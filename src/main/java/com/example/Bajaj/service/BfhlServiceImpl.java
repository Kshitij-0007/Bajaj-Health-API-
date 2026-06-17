package com.example.Bajaj.service;

import com.example.Bajaj.dto.BfhlRequest;
import com.example.Bajaj.dto.BfhlResponse;
import com.example.Bajaj.dto.SummaryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BfhlServiceImpl implements BfhlService {

    private static final Set<Character> VOWELS = Set.of('A', 'E', 'I', 'O', 'U');

    @Override
    public BfhlResponse process(BfhlRequest request, String requestId) {
        long startTime = System.currentTimeMillis();

        log.info("Processing request [requestId={}] with {} elements",
                requestId, request.getData() == null ? 0 : request.getData().size());

        List<Object> rawData = request.getData();
        int totalReceived = rawData == null ? 0 : rawData.size();

        // ── 1. Normalise raw input, filter invalid entries ────────────────────
        List<String> allStrings = new ArrayList<>();
        int invalidCount = 0;

        if (rawData != null) {
            for (Object item : rawData) {
                if (item == null) {
                    invalidCount++;
                    continue;
                }
                String s = item.toString().trim();
                if (s.isEmpty()) {
                    invalidCount++;
                    continue;
                }
                allStrings.add(s);
            }
        }

        // ── 2. Detect & remove duplicates ─────────────────────────────────────
        boolean containsDuplicates = allStrings.size() != new HashSet<>(allStrings).size();
        List<String> unique = new ArrayList<>(new LinkedHashSet<>(allStrings));
        int uniqueElementCount = unique.size();

        // ── 3. Classify each unique token ─────────────────────────────────────
        List<BigDecimal> numbers = new ArrayList<>();
        List<String> alphabetTokens = new ArrayList<>();
        List<Character> alphabetChars = new ArrayList<>();
        List<String> specialChars = new ArrayList<>();

        for (String token : unique) {
            TokenType type = classify(token);
            switch (type) {
                case NUMBER ->
                    numbers.add(new BigDecimal(token));

                case ALPHA -> {
                    alphabetTokens.add(token.toUpperCase());
                    for (char c : token.toUpperCase().toCharArray()) {
                        alphabetChars.add(c);
                    }
                }

                case SPECIAL ->
                    specialChars.add(token);

                case ALPHANUMERIC -> {
                    StringBuilder numBuf = new StringBuilder();
                    StringBuilder alphaBuf = new StringBuilder();

                    for (char c : token.toCharArray()) {
                        if (Character.isDigit(c)) {
                            numBuf.append(c);
                        } else if (Character.isLetter(c)) {
                            alphaBuf.append(c);
                        }
                    }

                    String numPart = numBuf.toString().trim();
                    String alphaPart = alphaBuf.toString().trim().toUpperCase();

                    if (!numPart.isEmpty() && isValidNumber(numPart)) {
                        numbers.add(new BigDecimal(numPart));
                    }
                    if (!alphaPart.isEmpty()) {
                        alphabetTokens.add(alphaPart);
                        for (char c : alphaPart.toCharArray()) {
                            alphabetChars.add(c);
                        }
                    }
                }
            }
        }

        int validCount = totalReceived - invalidCount;

        // ── 4. Number processing ──────────────────────────────────────────────
        Collections.sort(numbers);

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        BigDecimal sum = BigDecimal.ZERO;

        for (BigDecimal n : numbers) {
            sum = sum.add(n);
            if (n.stripTrailingZeros().scale() <= 0) {
                long longVal = n.longValueExact();
                if (Math.abs(longVal) % 2 != 0) {
                    oddNumbers.add(formatNumber(n));
                } else {
                    evenNumbers.add(formatNumber(n));
                }
            } else {
                // decimals go to even
                evenNumbers.add(formatNumber(n));
            }
        }

        String largestNumber = numbers.isEmpty() ? null : formatNumber(numbers.get(numbers.size() - 1));
        String smallestNumber = numbers.isEmpty() ? null : formatNumber(numbers.get(0));
        List<String> sortedNumbers = numbers.stream()
                .map(this::formatNumber)
                .collect(Collectors.toList());

        // ── 5. Alphabet processing ────────────────────────────────────────────
        Map<String, Integer> alphabetFrequency = new TreeMap<>();
        int vowelCount = 0;
        int consonantCount = 0;

        for (char c : alphabetChars) {
            String key = String.valueOf(c);
            alphabetFrequency.merge(key, 1, Integer::sum);
            if (VOWELS.contains(c)) {
                vowelCount++;
            } else {
                consonantCount++;
            }
        }

        String longestAlpha = alphabetTokens.isEmpty() ? null
                : alphabetTokens.stream()
                        .max(Comparator.comparingInt(String::length))
                        .orElse(null);

        String shortestAlpha = alphabetTokens.isEmpty() ? null
                : alphabetTokens.stream()
                        .min(Comparator.comparingInt(String::length))
                        .orElse(null);

        // ── 6. Build response ─────────────────────────────────────────────────
        long processingTime = System.currentTimeMillis() - startTime;

        BfhlResponse response = BfhlResponse.builder()
                .isSuccess(true)
                .requestId(requestId)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabetTokens)
                .specialCharacters(specialChars)
                .sum(formatNumber(sum))
                .largestNumber(largestNumber)
                .smallestNumber(smallestNumber)
                .sortedNumbers(sortedNumbers)
                .alphabetCount(alphabetChars.size())
                .numberCount(numbers.size())
                .specialCharacterCount(specialChars.size())
                .vowelCount(vowelCount)
                .consonantCount(consonantCount)
                .uniqueElementCount(uniqueElementCount)
                .alphabetFrequency(alphabetFrequency.isEmpty() ? null : alphabetFrequency)
                .longestAlphabeticValue(longestAlpha)
                .shortestAlphabeticValue(shortestAlpha)
                .containsDuplicates(containsDuplicates)
                .processingTimeMs(processingTime)
                .summary(SummaryDto.builder()
                        .totalElementsReceived(totalReceived)
                        .validElementsProcessed(validCount)
                        .invalidElementsIgnored(invalidCount)
                        .build())
                .build();

        log.info("Request [requestId={}] processed in {}ms", requestId, processingTime);

        return response;
    }

    // ── Helpers ───────────────────────────────────────────────────────────────
    private enum TokenType {
        NUMBER, ALPHA, SPECIAL, ALPHANUMERIC
    }

    private TokenType classify(String token) {
        if (isValidNumber(token)) {
            return TokenType.NUMBER;
        }

        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : token.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                hasSpecial = true;
            }
        }

        if (hasLetter && !hasDigit && !hasSpecial) {
            return TokenType.ALPHA;
        }
        if (!hasLetter && !hasDigit) {
            return TokenType.SPECIAL;
        }
        if (hasSpecial) {
            return TokenType.SPECIAL;
        }
        return TokenType.ALPHANUMERIC;
    }

    private boolean isValidNumber(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        try {
            new BigDecimal(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String formatNumber(BigDecimal n) {
        if (n == null) {
            return null;
        }
        BigDecimal stripped = n.stripTrailingZeros();
        return stripped.toPlainString();
    }
}

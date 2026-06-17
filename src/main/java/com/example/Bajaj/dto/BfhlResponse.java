package com.example.Bajaj.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BfhlResponse {

    @JsonProperty("is_success")
    private boolean isSuccess;

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("odd_numbers")
    private List<String> oddNumbers;

    @JsonProperty("even_numbers")
    private List<String> evenNumbers;

    @JsonProperty("alphabets")
    private List<String> alphabets;

    @JsonProperty("special_characters")
    private List<String> specialCharacters;

    @JsonProperty("sum")
    private String sum;

    @JsonProperty("largest_number")
    private String largestNumber;

    @JsonProperty("smallest_number")
    private String smallestNumber;

    @JsonProperty("sorted_numbers")
    private List<String> sortedNumbers;

    @JsonProperty("alphabet_count")
    private int alphabetCount;

    @JsonProperty("number_count")
    private int numberCount;

    @JsonProperty("special_character_count")
    private int specialCharacterCount;

    @JsonProperty("vowel_count")
    private int vowelCount;

    @JsonProperty("consonant_count")
    private int consonantCount;

    @JsonProperty("unique_element_count")
    private int uniqueElementCount;

    @JsonProperty("alphabet_frequency")
    private Map<String, Integer> alphabetFrequency;

    @JsonProperty("longest_alphabetic_value")
    private String longestAlphabeticValue;

    @JsonProperty("shortest_alphabetic_value")
    private String shortestAlphabeticValue;

    @JsonProperty("contains_duplicates")
    private boolean containsDuplicates;

    @JsonProperty("processing_time_ms")
    private long processingTimeMs;

    @JsonProperty("summary")
    private SummaryDto summary;

    // ── Getters & Setters ─────────────────────────────────────────────────────
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<String> getOddNumbers() {
        return oddNumbers;
    }

    public void setOddNumbers(List<String> oddNumbers) {
        this.oddNumbers = oddNumbers;
    }

    public List<String> getEvenNumbers() {
        return evenNumbers;
    }

    public void setEvenNumbers(List<String> evenNumbers) {
        this.evenNumbers = evenNumbers;
    }

    public List<String> getAlphabets() {
        return alphabets;
    }

    public void setAlphabets(List<String> alphabets) {
        this.alphabets = alphabets;
    }

    public List<String> getSpecialCharacters() {
        return specialCharacters;
    }

    public void setSpecialCharacters(List<String> specialCharacters) {
        this.specialCharacters = specialCharacters;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getLargestNumber() {
        return largestNumber;
    }

    public void setLargestNumber(String largestNumber) {
        this.largestNumber = largestNumber;
    }

    public String getSmallestNumber() {
        return smallestNumber;
    }

    public void setSmallestNumber(String smallestNumber) {
        this.smallestNumber = smallestNumber;
    }

    public List<String> getSortedNumbers() {
        return sortedNumbers;
    }

    public void setSortedNumbers(List<String> sortedNumbers) {
        this.sortedNumbers = sortedNumbers;
    }

    public int getAlphabetCount() {
        return alphabetCount;
    }

    public void setAlphabetCount(int alphabetCount) {
        this.alphabetCount = alphabetCount;
    }

    public int getNumberCount() {
        return numberCount;
    }

    public void setNumberCount(int numberCount) {
        this.numberCount = numberCount;
    }

    public int getSpecialCharacterCount() {
        return specialCharacterCount;
    }

    public void setSpecialCharacterCount(int specialCharacterCount) {
        this.specialCharacterCount = specialCharacterCount;
    }

    public int getVowelCount() {
        return vowelCount;
    }

    public void setVowelCount(int vowelCount) {
        this.vowelCount = vowelCount;
    }

    public int getConsonantCount() {
        return consonantCount;
    }

    public void setConsonantCount(int consonantCount) {
        this.consonantCount = consonantCount;
    }

    public int getUniqueElementCount() {
        return uniqueElementCount;
    }

    public void setUniqueElementCount(int uniqueElementCount) {
        this.uniqueElementCount = uniqueElementCount;
    }

    public Map<String, Integer> getAlphabetFrequency() {
        return alphabetFrequency;
    }

    public void setAlphabetFrequency(Map<String, Integer> alphabetFrequency) {
        this.alphabetFrequency = alphabetFrequency;
    }

    public String getLongestAlphabeticValue() {
        return longestAlphabeticValue;
    }

    public void setLongestAlphabeticValue(String longestAlphabeticValue) {
        this.longestAlphabeticValue = longestAlphabeticValue;
    }

    public String getShortestAlphabeticValue() {
        return shortestAlphabeticValue;
    }

    public void setShortestAlphabeticValue(String shortestAlphabeticValue) {
        this.shortestAlphabeticValue = shortestAlphabeticValue;
    }

    public boolean isContainsDuplicates() {
        return containsDuplicates;
    }

    public void setContainsDuplicates(boolean containsDuplicates) {
        this.containsDuplicates = containsDuplicates;
    }

    public long getProcessingTimeMs() {
        return processingTimeMs;
    }

    public void setProcessingTimeMs(long processingTimeMs) {
        this.processingTimeMs = processingTimeMs;
    }

    public SummaryDto getSummary() {
        return summary;
    }

    public void setSummary(SummaryDto summary) {
        this.summary = summary;
    }
}

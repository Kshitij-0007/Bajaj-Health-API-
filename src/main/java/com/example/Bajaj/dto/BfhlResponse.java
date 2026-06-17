package com.example.Bajaj.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}

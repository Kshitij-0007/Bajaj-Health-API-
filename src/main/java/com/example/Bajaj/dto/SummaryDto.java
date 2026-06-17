package com.example.Bajaj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummaryDto {

    @JsonProperty("total_elements_received")
    private int totalElementsReceived;

    @JsonProperty("valid_elements_processed")
    private int validElementsProcessed;

    @JsonProperty("invalid_elements_ignored")
    private int invalidElementsIgnored;
}

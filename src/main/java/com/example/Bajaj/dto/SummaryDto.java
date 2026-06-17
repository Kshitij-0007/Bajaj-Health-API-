package com.example.Bajaj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SummaryDto {

    @JsonProperty("total_elements_received")
    private int totalElementsReceived;

    @JsonProperty("valid_elements_processed")
    private int validElementsProcessed;

    @JsonProperty("invalid_elements_ignored")
    private int invalidElementsIgnored;

    public SummaryDto() {
    }

    public SummaryDto(int totalElementsReceived, int validElementsProcessed, int invalidElementsIgnored) {
        this.totalElementsReceived = totalElementsReceived;
        this.validElementsProcessed = validElementsProcessed;
        this.invalidElementsIgnored = invalidElementsIgnored;
    }

    public int getTotalElementsReceived() {
        return totalElementsReceived;
    }

    public void setTotalElementsReceived(int v) {
        this.totalElementsReceived = v;
    }

    public int getValidElementsProcessed() {
        return validElementsProcessed;
    }

    public void setValidElementsProcessed(int v) {
        this.validElementsProcessed = v;
    }

    public int getInvalidElementsIgnored() {
        return invalidElementsIgnored;
    }

    public void setInvalidElementsIgnored(int v) {
        this.invalidElementsIgnored = v;
    }
}

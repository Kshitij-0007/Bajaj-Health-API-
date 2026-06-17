package com.example.Bajaj.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class BfhlRequest {

    @NotNull(message = "data field is required and cannot be null")
    @JsonProperty("data")
    private List<Object> data;

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
}

package com.example.Bajaj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class BfhlRequest {

    @NotNull(message = "data field is required and cannot be null")
    @JsonProperty("data")
    private List<Object> data;
}

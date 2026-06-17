package com.example.Bajaj.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bajaj.dto.BfhlRequest;
import com.example.Bajaj.dto.BfhlResponse;
import com.example.Bajaj.service.BfhlService;

import jakarta.validation.Valid;

@RestController
public class BfhlController {

    private static final Logger log = LoggerFactory.getLogger(BfhlController.class);
    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping("/bfhl")
    public ResponseEntity<BfhlResponse> processData(
            @RequestHeader(value = "X-Request-Id", required = false) String requestId,
            @Valid @RequestBody BfhlRequest request) {

        log.info("Received POST /bfhl [X-Request-Id: {}]", requestId);
        return ResponseEntity.ok(bfhlService.process(request, requestId));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        log.info("GET /health called");
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "service", "bfhl-api",
                "timestamp", java.time.Instant.now().toString()
        ));
    }
}

package com.example.Bajaj.service;

import com.example.Bajaj.dto.BfhlRequest;
import com.example.Bajaj.dto.BfhlResponse;

public interface BfhlService {

    BfhlResponse process(BfhlRequest request, String requestId);
}

package com.streamrank.infrastructure.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StreamRankService {
    @Value("${spring.application.apiUrl}")
    private String apiBaseUrl;
    private final RestTemplate restTemplate = new RestTemplate();
}

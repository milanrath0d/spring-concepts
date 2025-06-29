package org.milan.ai.controller;

import org.milan.ai.model.GeminiModel;
import org.milan.ai.model.GeminiModelListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * @author Milan Rathod
 */
@RestController
@RequestMapping("/api/")
public class GeminiModelController {

    private static final Logger log = LoggerFactory.getLogger(GeminiModelController.class);

    @Value("${spring.ai.openai.api-key}")
    private String GEMINI_API_KEY;

    private final RestClient restClient;

    public GeminiModelController(RestClient.Builder builder) {
        this.restClient = builder
            .baseUrl("https://generativelanguage.googleapis.com")
            .build();
    }

    @GetMapping("/models")
    public List<GeminiModel> models() {
        ResponseEntity<GeminiModelListResponse> response = restClient.get()
            .uri("/v1beta/openai/models")
            .header("Authorization", "Bearer " + GEMINI_API_KEY)
            .retrieve()
            .toEntity(GeminiModelListResponse.class);
        return response.getBody().data();
    }
}

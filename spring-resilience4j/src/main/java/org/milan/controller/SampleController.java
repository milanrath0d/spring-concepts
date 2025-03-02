package org.milan.controller;

import org.milan.service.SampleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Milan Rathod
 */
@RestController
public class SampleController {
    private final SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/test/circuit-breaker")
    public String testCircuitBreaker() {
        return sampleService.callExternalService();
    }

    @GetMapping("/test/retry")
    public String testRetry() {
        return sampleService.callExternalService2();
    }
}

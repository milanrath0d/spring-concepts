package org.milan.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Milan Rathod
 */
@Service
public class SampleService {
    private static final Logger log = LoggerFactory.getLogger(SampleService.class);

    public static final String SAMPLE_SERVICE = "sampleService";

    @CircuitBreaker(name = SAMPLE_SERVICE, fallbackMethod = "fallbackResponse")
    public String callExternalService() {
        if (Math.random() > 0.5) {
            throw new RuntimeException("Service Failed!");
        }
        return "Service Call Success!";
    }

    @Retry(name = SAMPLE_SERVICE, fallbackMethod = "fallbackResponse")
    public String callExternalService2() {
        log.info("call external service 2");
        if (Math.random() > 0.01) {
            throw new RuntimeException("Service Failed!");
        }
        return "Service Call Success!";
    }

    public String fallbackResponse(Exception ex) {
        return "Fallback Response!" + ex.getMessage();
    }
}

package org.milan.virtual.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Service that simulates long-running tasks to demonstrate virtual threads
 */
@Service
public class SimulatedWorkService {
    
    private static final Logger logger = LoggerFactory.getLogger(SimulatedWorkService.class);
    
    /**
     * Simulates a database query that takes a variable amount of time
     * @param id The ID to look up
     * @return A result string
     */
    public String simulateDatabaseQuery(Long id) {
        String threadName = Thread.currentThread().toString();
        logger.info("Database query for id {} started on thread {}", id, threadName);
        
        // Simulate database latency (500-1500ms)
        long sleepTime = ThreadLocalRandom.current().nextLong(500, 1500);
        try {
            Thread.sleep(Duration.ofMillis(sleepTime));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        logger.info("Database query for id {} completed after {}ms on thread {}", 
                id, sleepTime, threadName);
        
        return "Result for ID: " + id + " (processed by " + threadName + ")";
    }
    
    /**
     * Simulates an external API call that takes a variable amount of time
     * @param resource The resource to request
     * @return A result string
     */
    public String simulateExternalApiCall(String resource) {
        String threadName = Thread.currentThread().toString();
        logger.info("External API call for resource {} started on thread {}", resource, threadName);
        
        // Simulate API latency (800-2000ms)
        long sleepTime = ThreadLocalRandom.current().nextLong(800, 2000);
        try {
            Thread.sleep(Duration.ofMillis(sleepTime));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        logger.info("External API call for resource {} completed after {}ms on thread {}", 
                resource, sleepTime, threadName);
        
        return "API response for: " + resource + " (processed by " + threadName + ")";
    }
} 
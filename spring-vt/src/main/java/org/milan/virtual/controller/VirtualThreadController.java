package org.milan.virtual.controller;

import org.milan.virtual.service.SimulatedWorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * REST controller that demonstrates the use of virtual threads
 */
@RestController
@RequestMapping("/api/vt")
public class VirtualThreadController {

    private static final Logger logger = LoggerFactory.getLogger(VirtualThreadController.class);
    
    @Autowired
    private SimulatedWorkService workService;
    
    @Autowired
    private TaskExecutor taskExecutor;
    
    /**
     * Endpoint that performs sequential operations
     * @param count Number of operations to perform
     * @return List of results
     */
    @GetMapping("/sequential")
    public List<String> performSequentialQueries(@RequestParam(defaultValue = "5", name = "count") int count) {
        logger.info("Starting {} sequential operations", count);
        long startTime = System.currentTimeMillis();
        
        List<String> results = LongStream.range(1, count + 1)
                .mapToObj(workService::simulateDatabaseQuery)
                .collect(Collectors.toList());
        
        long duration = System.currentTimeMillis() - startTime;
        logger.info("Completed {} sequential operations in {}ms", count, duration);
        
        return results;
    }
    
    /**
     * Endpoint that performs parallel operations using Spring's TaskExecutor
     * (which will use virtual threads with spring.threads.virtual.enabled=true)
     * @param count Number of operations to perform
     * @return List of results
     */
    @GetMapping("/parallel")
    public List<String> performParallelQueries(@RequestParam(defaultValue = "5", name = "count") int count) {
        logger.info("Starting {} parallel operations with virtual threads", count);
        long startTime = System.currentTimeMillis();
        
        List<CompletableFuture<String>> futures = LongStream.range(1, count + 1)
                .mapToObj(id -> CompletableFuture.supplyAsync(
                        () -> workService.simulateDatabaseQuery(id), 
                        task -> taskExecutor.execute(task)))
                .toList();
        
        List<String> results = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        
        long duration = System.currentTimeMillis() - startTime;
        logger.info("Completed {} parallel operations in {}ms", count, duration);
        
        return results;
    }
    
    /**
     * Endpoint that combines multiple operations in parallel
     * @return Combined results from both operations
     */
    @GetMapping("/combined")
    public Map<String, Object> performCombinedOperations() {
        logger.info("Starting combined parallel operations");
        long startTime = System.currentTimeMillis();
        
        // Create futures for 3 simulated DB queries
        CompletableFuture<List<String>> dbFuture = CompletableFuture.supplyAsync(() -> 
            LongStream.range(1, 4)
                .mapToObj(id -> CompletableFuture.supplyAsync(
                    () -> workService.simulateDatabaseQuery(id), 
                    task -> taskExecutor.execute(task)))
                .toList()
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList()), 
            task -> taskExecutor.execute(task));
        
        // Create futures for 2 simulated API calls
        CompletableFuture<List<String>> apiFuture = CompletableFuture.supplyAsync(() ->
            List.of("users", "products")
                .stream()
                .map(resource -> CompletableFuture.supplyAsync(
                    () -> workService.simulateExternalApiCall(resource), 
                    task -> taskExecutor.execute(task)))
                .toList()
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList()),
            task -> taskExecutor.execute(task));
        
        // Wait for all operations to complete
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(dbFuture, apiFuture);
        allFutures.join();
        
        long duration = System.currentTimeMillis() - startTime;
        logger.info("Completed combined parallel operations in {}ms", duration);
        
        // Return combined results
        return Map.of(
            "dbResults", dbFuture.join(),
            "apiResults", apiFuture.join(),
            "totalDurationMs", duration
        );
    }
    
    /**
     * Thread information endpoint to see details about the current thread
     * @return Information about the current thread
     */
    @GetMapping("/thread-info")
    public Map<String, Object> getThreadInfo() {
        Thread currentThread = Thread.currentThread();
        return Map.of(
            "threadName", currentThread.getName(),
            "threadId", currentThread.getId(),
            "isVirtual", currentThread.isVirtual(),
            "threadDetails", currentThread.toString()
        );
    }
} 
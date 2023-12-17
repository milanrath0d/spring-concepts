package com.example.scheduling.retry;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author Milan Rathod
 */
@Configuration
@ComponentScan(basePackages = "com.example.scheduling.retry")
@EnableRetry
public class RetryConfig {

}

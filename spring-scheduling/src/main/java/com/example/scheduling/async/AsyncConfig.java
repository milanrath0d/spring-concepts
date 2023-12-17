package com.example.scheduling.async;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Milan Rathod
 */
@Configuration
@EnableAsync
@ComponentScan("com.example.scheduling.async")
public class AsyncConfig {
}

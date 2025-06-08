# Spring Virtual Threads Demo

This module demonstrates the use of Java's Virtual Threads (Project Loom) with Spring Boot.

## Overview

Virtual Threads are lightweight threads that are managed by the JVM rather than the operating system. They are designed to be efficient for I/O-bound applications, where a large number of threads spend most of their time waiting for I/O operations to complete.

This demo showcases:

- How to use Spring Boot's built-in virtual thread support
- Performance comparison between sequential and parallel execution
- Practical examples of Virtual Threads in RESTful APIs

## Requirements

- Java 21 or higher (Virtual Threads were officially released in Java 21)
- Spring Boot 3.2+ (which has built-in support for Virtual Threads)

## Features

1. **Virtual Thread Configuration**
   - Uses Spring Boot 3.2.1's built-in virtual thread support with the `spring.threads.virtual.enabled=true` property
   - No custom configuration needed - Spring Boot automatically configures Tomcat and task executors to use virtual threads

2. **Demo Endpoints**
   - `/api/vt/sequential` - Simulates I/O operations running sequentially
   - `/api/vt/parallel` - Performs the same operations in parallel using Virtual Threads
   - `/api/vt/combined` - Demonstrates combining multiple async operations
   - `/api/vt/thread-info` - Shows information about the current thread

## Running the Application

```bash
mvn spring-boot:run
```

Then visit http://localhost:8080/api/vt/thread-info to verify that the application is using Virtual Threads.

## Performance Testing

To compare the performance of sequential vs. parallel execution:

1. Try `/api/vt/sequential?count=10` and note the execution time
2. Try `/api/vt/parallel?count=10` and note the execution time
3. The parallel execution should be significantly faster, especially as you increase the count

## Implementation Notes

- This demo simulates I/O-bound operations (like database queries and API calls) with artificial delays
- No actual database is used - the SimulatedWorkService class simply uses delays to mimic I/O operations
- Virtual Threads show the most benefit for I/O-bound operations, not CPU-bound tasks
- The implementation focuses purely on demonstrating virtual thread behavior and performance
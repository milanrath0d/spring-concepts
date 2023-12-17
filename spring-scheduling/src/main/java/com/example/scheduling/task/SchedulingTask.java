package com.example.scheduling.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Milan Rathod
 */
//@Component
@Slf4j
public class SchedulingTask {

    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void printFixedRateString() {
        log.info("Inside fixedRateString method");
    }

    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
    public void printFixedDelayString() {
        log.info("Inside fixedDelayString method");
    }

    @Scheduled(fixedRate = 10000)
    public void printFixedRate() {
        log.info("Inside fixedRate method");
    }

    @Scheduled(fixedDelay = 15000)
    public void printFixedDelay() {
        log.info("Inside fixedDelay method");
    }
}

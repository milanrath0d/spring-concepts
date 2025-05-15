package com.example.scheduling.taskscheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Milan Rathod
 */
@Component
public class ThreadPoolTaskSchedulerExamples {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Autowired
    private CronTrigger cronTrigger;

    @Autowired
    private PeriodicTrigger periodicTrigger;

    //@PostConstruct
    public void schedulerRunnable() {
        threadPoolTaskScheduler.schedule(new RunnableTask("Current Date"), new Date());
        threadPoolTaskScheduler.scheduleWithFixedDelay(new RunnableTask("Fixed 1 second Delay"), 1000);
        threadPoolTaskScheduler.scheduleWithFixedDelay(new RunnableTask("Current Date Fixed 1 second Delay"), new Date(), 1000);
        threadPoolTaskScheduler.scheduleAtFixedRate(new RunnableTask("Fixed Rate of 2 seconds"), new Date(), 2000);
        threadPoolTaskScheduler.scheduleAtFixedRate(new RunnableTask("Fixed Rate of 2 seconds"), 2000);
        threadPoolTaskScheduler.schedule(new RunnableTask("Cron Trigger"), cronTrigger);
        threadPoolTaskScheduler.schedule(new RunnableTask("Periodic Trigger"), periodicTrigger);
    }

    static class RunnableTask implements Runnable {

        private final String message;

        public RunnableTask(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            System.out.println("Runnable Task with " + message + " on thread " + Thread.currentThread().getName());
        }
    }
}

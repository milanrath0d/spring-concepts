package com.example.scheduling.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author Milan Rathod
 */
@Service
@Slf4j
public class AsyncService {

    @Async
    public void asyncMethodWithVoidReturnType() {
        log.info("Executed method asynchronously " + Thread.currentThread().getName());
    }

    @Async
    public Future<String> asyncMethodWithReturnType() {
        log.info("Execute method asynchronously " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            return new AsyncResult<>("hello world !!!!");
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

}

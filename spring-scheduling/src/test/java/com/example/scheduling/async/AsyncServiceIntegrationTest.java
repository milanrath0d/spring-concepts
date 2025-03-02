package com.example.scheduling.async;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Milan Rathod
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AsyncConfig.class}, loader = AnnotationConfigContextLoader.class)
class AsyncServiceIntegrationTest {

    @Autowired
    private AsyncService asyncService;

    @Test
    @Disabled
    void testAsyncAnnotationForMethodsWithVoidReturnType() {
        asyncService.asyncMethodWithReturnType();
    }

    @Test
    @Disabled
    void testAsyncAnnotationForMethodsWithReturnType() throws InterruptedException, ExecutionException {
        Future<String> future = asyncService.asyncMethodWithReturnType();

        while (true) {
            if (future.isDone()) {
                System.out.println("Result from asynchronous process - " + future.get());
                break;
            }
            System.out.println("Continue doing something else. ");
            Thread.sleep(1000);
        }
    }

}
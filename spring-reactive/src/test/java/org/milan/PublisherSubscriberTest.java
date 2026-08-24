package org.milan;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

/**
 * @author Milan Rathod
 */
public class PublisherSubscriberTest {

    @Test
    public void publisherSubscriberTest() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
            .log();
        stringFlux.subscribe(new CustomSubscriber());
    }

    private static class CustomSubscriber implements Subscriber<String> {

        @Override
        public void onSubscribe(Subscription subscription) {
            System.out.println("onSubscribe");
            subscription.request(Long.MAX_VALUE);
        }

        @Override
        public void onNext(String s) {
            System.out.println("onNext: " + s);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("onError: " + throwable.getMessage());
        }

        @Override
        public void onComplete() {
            System.out.println("onComplete");
        }
    }
}



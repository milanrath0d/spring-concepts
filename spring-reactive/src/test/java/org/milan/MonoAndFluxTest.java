package org.milan;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * @author Milan Rathod
 */
public class MonoAndFluxTest {

    @Test
    public void fluxTestElements_Success() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
            .log();

        StepVerifier.create(stringFlux)
            .expectNext("Spring")
            .expectNext("Spring Boot")
            .expectNext("Reactive Spring")
            .verifyComplete();
    }

    @Test
    public void fluxTestElements_Error() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
            .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
            .log();

        StepVerifier.create(stringFlux)
            .expectNext("Spring")
            .expectNext("Spring Boot")
            .expectNext("Reactive Spring")
            .expectError(RuntimeException.class)
            .verify();
    }

    @Test
    public void fluxTestElementsCount() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
            .log();

        StepVerifier.create(stringFlux)
            .expectNextCount(3)
            .verifyComplete();
    }
}

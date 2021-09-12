package org.milan.client;

import org.milan.model.Employee;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Milan Rathod
 */
public class EmployeeWebClient {

    private final WebClient webClient;

    public EmployeeWebClient() {
        webClient = WebClient.create("http://localhost:7001");
    }

    public void consume() {
        Mono<Employee> employeeMono = webClient.get()
            .uri("/employees/{id}", "1")
            .retrieve()
            .bodyToMono(Employee.class);

        employeeMono.subscribe(System.out::println);

        Flux<Employee> employeeFlux = webClient.get()
            .uri("/employees")
            .retrieve()
            .bodyToFlux(Employee.class);

        employeeFlux.subscribe(System.out::println);
    }
}

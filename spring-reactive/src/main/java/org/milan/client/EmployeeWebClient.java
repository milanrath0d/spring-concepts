package org.milan.client;

import org.milan.model.Employee;
import org.milan.request.CreateEmployeeRequest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Milan Rathod
 */
public class EmployeeWebClient {

    private static final String EMPLOYEE_API_BASE_PATH = "/employees";

    private final WebClient webClient;

    public EmployeeWebClient() {
        webClient = WebClient.create("http://localhost:7001");
    }

    public void consume() {
        CreateEmployeeRequest request  = CreateEmployeeRequest.builder()
            .name("test")
            .salary(10000L)
            .build();

        webClient.delete()
            .uri(EMPLOYEE_API_BASE_PATH)
            .retrieve()
            .bodyToMono(Void.class)
            .block();

        webClient.post()
            .uri(EMPLOYEE_API_BASE_PATH)
            .bodyValue(request)
            .retrieve()
            .bodyToMono(Employee.class)
            .block();

        Mono<Employee> employeeMono = webClient.get()
            .uri("/employees/{name}", "test")
            .retrieve()
            .bodyToMono(Employee.class);

        employeeMono.subscribe(System.out::println);

        Flux<Employee> employeeFlux = webClient.get()
            .uri(EMPLOYEE_API_BASE_PATH)
            .retrieve()
            .bodyToFlux(Employee.class);

        employeeFlux.subscribe(System.out::println);

        webClient.get()
            .uri("/employees/{name}", "test1")
            .retrieve()
            .onStatus(statusCode -> statusCode.is4xxClientError() || statusCode.is5xxServerError(), clientResponse -> {
                System.err.println(clientResponse);
                return clientResponse.bodyToMono(String.class)
                    .flatMap(error -> Mono.error(new RuntimeException(error)));
            })
            .bodyToMono(String.class)
            .block();
    }
}

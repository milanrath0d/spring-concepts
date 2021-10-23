package org.milan.controller;

import org.junit.Test;
import org.milan.model.Employee;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class TestEmployee {

    @Test
    public void test() {
        DummyEmployeeService dummyEmployeeService = new DummyEmployeeService();

        Mono<Employee> employeeMono = dummyEmployeeService.getEmployee(2, "true");

        StepVerifier.create(employeeMono.log())
            .expectNext(new Employee("transformedTest", "transformedTest"))
            .verifyComplete();
    }

    @Test
    public void test2() {
        DummyEmployeeService dummyEmployeeService = new DummyEmployeeService();

        Mono<Employee> employeeMono = dummyEmployeeService.getEmployee(2, "false");

        StepVerifier.create(employeeMono.log())
            .verifyComplete();
    }
}

class DummyEmployeeService {
    public Mono<Employee> getEmployee(int count, String flag) {
        return Mono.fromSupplier(() -> {
                if (count == 1) {
                    return new Employee("test", "test");
                }
                return isTrue(flag);
            })
            .flatMap(result -> {
                System.out.println("Test");
                return Mono.fromSupplier(() -> new Employee("transformedTest", "transformedTest"));
            })
            .onErrorResume(error -> {
                System.out.println("Failed");
                return Mono.empty();
            });
    }

    private Boolean isTrue(String flag) {
        if (!flag.equals("true")) {
            throw new RuntimeException("test");
        }
        return Boolean.TRUE;
    }
}

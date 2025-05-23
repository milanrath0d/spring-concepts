package org.milan.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.milan.EmployeeReactiveApplication;
import org.milan.model.Employee;
import org.milan.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * @author Milan Rathod
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EmployeeReactiveApplication.class)
public class EmployeeIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenCorrectEmployee() {
        Employee employee = constructEmployee("1", "Employee1");

        when(employeeRepository.findByName("Employee1")).thenReturn(Mono.just(employee));

        webTestClient.get()
            .uri("/employees/Employee1")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(Employee.class)
            .isEqualTo(employee);
    }

    @Test
    public void whenGetAllEmployees_thenCorrectEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        Employee employee1 = constructEmployee("1", "Employee 1 Name");
        Employee employee2 = constructEmployee("2", "Employee 2 Name");
        Employee employee3 = constructEmployee("3", "Employee 3 Name");

        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);

        Flux<Employee> employeeFlux = Flux.fromIterable(employeeList);

        when(employeeRepository.findAll()).thenReturn(employeeFlux);

        webTestClient.get()
            .uri("/employees")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBodyList(Employee.class)
            .hasSize(3)
            .isEqualTo(employeeList);
    }

    private Employee constructEmployee(String id, String name) {
        return Employee.builder()
          .id(id)
          .name(name)
          .build();
    }

}
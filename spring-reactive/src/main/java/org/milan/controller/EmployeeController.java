package org.milan.controller;

import lombok.AllArgsConstructor;
import org.milan.EmployeeService;
import org.milan.model.Employee;
import org.milan.request.CreateEmployeeRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Milan Rathod
 */
@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping("/{name}")
    public Mono<Employee> getByName(@PathVariable String name) {
        return employeeService.findByName(name);
    }

    @PostMapping
    public Mono<Employee> create(@RequestBody CreateEmployeeRequest createEmployeeRequest) {
        final Employee employee = Employee.builder()
            .name(createEmployeeRequest.getName())
            .salary(createEmployeeRequest.getSalary())
            .build();
        return employeeService.create(employee);
    }

    @GetMapping
    public Flux<Employee> getAll() {
        return employeeService.findAll();
    }

    @DeleteMapping
    public Mono<Void> deleteAll() {
        return employeeService.deleteAll();
    }

}

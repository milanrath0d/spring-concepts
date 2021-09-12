package org.milan.controller;

import lombok.AllArgsConstructor;
import org.milan.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.milan.repository.EmployeeRepository;

/**
 * @author Milan Rathod
 */
@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    @GetMapping("/{id}")
    public Mono<Employee> getById(@PathVariable String id) {
        return employeeRepository.findById(id);
    }

    @GetMapping
    public Flux<Employee> getAll() {
        return employeeRepository.findAll();
    }

}

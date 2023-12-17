package org.milan;

import lombok.AllArgsConstructor;
import org.milan.model.Employee;
import org.milan.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Milan Rathod
 */
@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Mono<Employee> findByName(String name) {
        return employeeRepository.findByName(name)
            .switchIfEmpty(Mono.error(new IllegalStateException("employee doesn't exist")));
    }

    public Mono<Employee> create(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Flux<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Mono<Void> deleteAll() {
        return employeeRepository.deleteAll();
    }
}

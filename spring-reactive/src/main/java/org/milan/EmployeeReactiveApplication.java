package org.milan;

import org.milan.client.EmployeeWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Milan Rathod
 */
@SpringBootApplication
public class EmployeeReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeReactiveApplication.class, args);

        EmployeeWebClient employeeWebClient = new EmployeeWebClient();

        employeeWebClient.consume();
    }
}

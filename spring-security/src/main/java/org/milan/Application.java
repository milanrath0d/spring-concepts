package org.milan;

import org.milan.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Application startup class
 *
 * @author Milan Rathod
 */
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
package org.milan.ai;

import org.milan.ai.configuration.WeatherConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author milanrathod
 */
@EnableConfigurationProperties(WeatherConfigurationProperties.class)
@SpringBootApplication
public class SpringAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiApplication.class, args);
    }

}
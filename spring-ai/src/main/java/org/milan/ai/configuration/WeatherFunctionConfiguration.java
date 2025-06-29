package org.milan.ai.configuration;

import org.milan.ai.service.WeatherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import java.util.function.Function;

/**
 * @author Milan Rathod
 */
@Configuration
public class WeatherFunctionConfiguration {

    private final WeatherConfigurationProperties weatherConfigurationProperties;

    public WeatherFunctionConfiguration(WeatherConfigurationProperties weatherConfigurationProperties) {
        this.weatherConfigurationProperties = weatherConfigurationProperties;
    }

    @Bean
    @Description("Get the current weather conditions for the given city.")
    public Function<WeatherService.Request, WeatherService.Response> currentWeatherFunction() {
        return new WeatherService(weatherConfigurationProperties);
    }

}

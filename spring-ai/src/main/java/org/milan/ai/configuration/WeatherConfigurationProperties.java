package org.milan.ai.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Milan Rathod
 */
@ConfigurationProperties(prefix = "weather")
public record WeatherConfigurationProperties(String apiKey, String apiUrl) {
}

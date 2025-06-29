package org.milan.ai.service;

import org.milan.ai.configuration.WeatherConfigurationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

/**
 * @author Milan Rathod
 */
@Service
public class WeatherService implements Function<WeatherService.Request, WeatherService.Response> {

    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);

    private final RestClient restClient;

    private final WeatherConfigurationProperties weatherConfigurationProperties;

    public WeatherService(WeatherConfigurationProperties weatherConfigurationProperties) {
        this.restClient = RestClient.create(weatherConfigurationProperties.apiUrl());
        this.weatherConfigurationProperties = weatherConfigurationProperties;
    }

    @Override
    public Response apply(Request request) {
        log.info("Getting weather for city: {}", request.city());

        final Response response = restClient.get()
            .uri("/current.json?key={apiKey}&q={city}", weatherConfigurationProperties.apiKey(), request.city())
            .retrieve()
            .body(Response.class);

        log.info("Weather API Response: {}", response);
        return response;
    }

    // mapping the response of the Weather API to records. I only mapped the information I was interested in.
    public record Request(String city) {
    }

    public record Response(Location location, Current current) {
    }

    public record Location(String name, String region, String country, Long lat, Long lon) {
    }

    public record Current(String temp_f, Condition condition, String wind_mph, String humidity) {
    }

    public record Condition(String text) {
    }
}

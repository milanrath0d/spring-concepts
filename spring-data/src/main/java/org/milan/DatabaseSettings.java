package org.milan;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * MongoDB database settings
 *
 * @author Milan Rathod
 */
@Component
@Getter
public class DatabaseSettings {

    @Value("${spring.data.mongodb.host:localhost}")
    private String host;

    @Value("${spring.data.mongodb.port:27017}")
    private int port;

    @Value("${spring.data.mongodb.database:test}")
    private String databaseName;
}

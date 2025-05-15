package org.milan;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Mongo Configuration
 *
 * @author Milan Rathod
 */
@Configuration
@EnableMongoRepositories(basePackages = "org.milan.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Autowired
    private DatabaseSettings databaseSettings;

    @Override
    @Bean
    public MongoClient mongoClient() {
        String connectionString = "mongodb://" + databaseSettings.getHost() + ":" + databaseSettings.getPort();
        return MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build());
    }

    @Override
    protected String getDatabaseName() {
        return databaseSettings.getDatabaseName();
    }
}

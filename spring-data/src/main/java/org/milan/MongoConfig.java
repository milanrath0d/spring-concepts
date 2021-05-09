package org.milan;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Mongo Configuration
 *
 * @author Milan Rathod
 */
@Configuration
@EnableMongoRepositories(basePackages = "org.milan.repository")
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    private DatabaseSettings databaseSettings;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(databaseSettings.getHost(), databaseSettings.getPort());
    }

    @Override
    protected String getDatabaseName() {
        return databaseSettings.getDatabaseName();
    }
}

package org.milan;

import org.glassfish.jersey.server.ResourceConfig;
import org.milan.filter.PoweredByResponseFilter;
import org.milan.resource.DateResource;
import org.milan.resource.CustomResource;
import org.milan.resource.MessageResource;
import org.milan.resource.ProfileResource;
import org.milan.resource.TestResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

/**
 * Application Config class
 *
 * @author Milan Rathod
 */
@Component
@ApplicationPath("api")
public class Application extends ResourceConfig {

    @PostConstruct
    public void init() {
        register(TestResource.class);
        register(ProfileResource.class);
        register(MessageResource.class);
        register(PoweredByResponseFilter.class);
        register(DateResource.class);
        register(CustomResource.class);
        packages("org.milan");
    }

}

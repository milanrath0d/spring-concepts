package org.milan.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Secured Resource
 *
 * @author Milan Rathod
 */
@Path("secured")
public class SecuredResource {

    @GET
    @Path("message")
    @Produces(MediaType.TEXT_PLAIN)
    public String securedMethod() {
        return "This API is secured";
    }
}

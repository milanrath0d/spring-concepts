package org.milan.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

package org.milan.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

/**
 * Test Resource to verify various JAX-RS features
 * <p>
 * Resource life cycle: By default it is per request scope
 * For singleton resource creation, annotate resource class with
 * {@link @Singleton} annotation
 *
 * @author Milan Rathod
 */
@Path("{pathParam}/test")
public class TestResource {

    private int count;

    @PathParam("pathParam")
    private String pathParamExample;

    @QueryParam("query")
    private String queryParamExample;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String testMethod() {
        count += 1;

        // 1 should be printed for every request
        System.out.println(count);

        return "Path Param: " + pathParamExample + " Query Param: " + queryParamExample;
    }
}

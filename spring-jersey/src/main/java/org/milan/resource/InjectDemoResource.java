package org.milan.resource;

import org.springframework.stereotype.Component;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

/**
 * Test Resource
 *
 * @author Milan Rathod
 */
@Path("/injectDemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
@Component
public class InjectDemoResource {

    @GET
    @Path("annotations")
    public String getParamUsingAnnotations(@MatrixParam("param") String matrixParam,
                                           @HeaderParam("customHeaderParam") String headerParam,
                                           @CookieParam("JSESSIONID") String cookie) {
        return "Matrix Param " + matrixParam + " Header Param " + headerParam + " Cookie " + cookie;
    }

    @GET
    @Path("context")
    public String getParamUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders httpHeaders) {
        String path = uriInfo.getAbsolutePath().toString();
        String cookie = httpHeaders.getCookies().toString();
        return path + "  " + cookie;
    }
}

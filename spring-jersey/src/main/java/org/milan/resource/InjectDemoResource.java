package org.milan.resource;

import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

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

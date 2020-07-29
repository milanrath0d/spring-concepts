package org.milan.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Filter responsible for logging Request and Response Headers
 *
 * @author Milan Rathod
 */
@Provider
public class LoggingFilter implements ContainerResponseFilter, ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        System.out.println("Request : " + containerRequestContext.getHeaders());
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        System.out.println("Response : " + containerResponseContext.getHeaders());
    }
}

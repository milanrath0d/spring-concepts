package org.milan.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

/**
 * Filter responsible for checking basic auth security
 *
 * @author Milan Rathod
 */
@Provider
public class SecurityFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";

    private static final String SECURED_URL_PREFIX = "secured";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if (containerRequestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
            checkAuth(containerRequestContext);
        }
    }

    private void checkAuth(ContainerRequestContext containerRequestContext) {
        List<String> authHeader = containerRequestContext.getHeaders().get(AUTHORIZATION_HEADER);

        if (authHeader != null && !authHeader.isEmpty()) {
            String authToken = authHeader.get(0);
            authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");

            String userPass = new String(Base64.getDecoder().decode(authToken), StandardCharsets.UTF_8);

            String[] result = userPass.split(":");

            if ("user".equals(result[0]) && "pass".equals(result[1])) {
                return;
            }
        }
        Response response = Response
            .status(Response.Status.UNAUTHORIZED)
            .entity("User can not access resource")
            .build();
        containerRequestContext.abortWith(response);
    }
}

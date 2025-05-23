package org.milan.exception;

import org.milan.model.ErrorMessage;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;

/**
 * Generic exception mapper
 *
 * @author Milan Rathod
 */
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable throwable) {
        ErrorMessage errorMessage = new ErrorMessage(500, throwable.getMessage(), "generic error");
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
    }

}

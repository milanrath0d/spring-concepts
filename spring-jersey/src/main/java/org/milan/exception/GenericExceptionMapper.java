package org.milan.exception;

import org.milan.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Generic exception mapper
 *
 * @author Milan Rathod
 */
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exp) {
        ErrorMessage errorMessage = new ErrorMessage(exp.getMessage(), 500, "generic error");
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
    }

}

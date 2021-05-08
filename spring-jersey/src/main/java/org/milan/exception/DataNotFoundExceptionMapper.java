package org.milan.exception;

import org.milan.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception mapper for {@link DataNotFoundException}
 *
 * @author Milan Rathod
 */
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

    @Override
    public Response toResponse(DataNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(404, exception.getMessage(), "Error");
        return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
    }

}

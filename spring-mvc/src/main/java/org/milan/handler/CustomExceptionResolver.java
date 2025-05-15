package org.milan.handler;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom {@link org.springframework.web.servlet.HandlerExceptionResolver}
 *
 * @author Milan Rathod
 */
@Component
public class CustomExceptionResolver extends AbstractHandlerExceptionResolver {

    @Override
    protected ModelAndView doResolveException(@NonNull HttpServletRequest request,
                                              @NonNull HttpServletResponse response,
                                              Object o,
                                              Exception ex) {
        try {
            if (ex instanceof IllegalArgumentException) {
                return handleIllegalArgument(request, response, (IllegalArgumentException) ex);
            }
        } catch (Exception handlerException) {
            logger.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found", handlerException);
        }
        return null;
    }

    private ModelAndView handleIllegalArgument(HttpServletRequest request,
                                               HttpServletResponse response,
                                               IllegalArgumentException ex) throws IOException {
        return new ModelAndView("Error");
    }
}

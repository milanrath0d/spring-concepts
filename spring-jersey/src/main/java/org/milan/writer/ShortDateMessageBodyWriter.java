package org.milan.writer;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;

/**
 * Custom {@link MessageBodyWriter} for short date
 *
 * @author Milan Rathod
 */
@Provider
@Produces("text/shortdate")
public class ShortDateMessageBodyWriter implements MessageBodyWriter<Date> {

    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return Date.class.isAssignableFrom(aClass);
    }

    @Override
    public void writeTo(Date date, Class<?> aClass, Type type,
                        Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> multivaluedMap,
                        OutputStream outputStream) throws IOException, WebApplicationException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String shortDate = calendar.get(Calendar.DATE) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.YEAR);
        outputStream.write(shortDate.getBytes());
    }
}

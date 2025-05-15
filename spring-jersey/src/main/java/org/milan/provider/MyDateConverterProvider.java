package org.milan.provider;

import org.milan.model.MyDate;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;

/**
 * Provider for MyDate converter
 *
 * @author Milan Rathod
 */
@Provider
public class MyDateConverterProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {

        if (rawType.isAssignableFrom(MyDate.class)) {
            return new ParamConverter<T>() {
                @Override
                public T fromString(String value) {
                    Calendar calendar = Calendar.getInstance();

                    if ("tomorrow".equalsIgnoreCase(value)) {
                        calendar.add(Calendar.DATE, 1);
                    } else if ("yesterday".equalsIgnoreCase(value)) {
                        calendar.add(Calendar.DATE, -1);
                    }
                    MyDate myDate = new MyDate();

                    myDate.setDate(calendar.get(Calendar.DATE));
                    myDate.setMonth(calendar.get(Calendar.MONTH));
                    myDate.setYear(calendar.get(Calendar.YEAR));
                    return rawType.cast(myDate);
                }

                @Override
                public String toString(T t) {
                    return t != null ? t.toString() : null;
                }
            };
        }

        return null;
    }
}

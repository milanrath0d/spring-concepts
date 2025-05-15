package org.milan.resource;

import org.milan.model.MyDate;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.Calendar;
import java.util.Date;

/**
 * Resource for Date
 *
 * @author Milan Rathod
 */
@Path("date")
public class DateResource {

    private static final String TEXT_SHORT_DATE = "text/shortdate";

    @GET
    @Path("/{dateString}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getRequestedDate(@PathParam("dateString") MyDate myDate) {
        return myDate.toString();
    }

    @GET
    @Produces(TEXT_SHORT_DATE)
    public Date testDate() {
        return Calendar.getInstance().getTime();
    }
}

package org.milan.resource;

import org.milan.model.MyDate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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

package org.milan.resource;

import org.milan.model.SortParams;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Custom resource for testing purpose
 *
 * @author Milan Rathod
 */
@Path("/custom")
@Produces(MediaType.APPLICATION_JSON)
public class CustomResource {

    /**
     * For given request i.e. custom?sort=sort1&sort=sort2
     */
    @GET
    public List<String> getAllParamValues(@QueryParam("sort") List<String> sortOrders) {
        return sortOrders;
    }

    /**
     * For given request i.e. custom?sort=sort1,sort2,sort3
     */
    @GET
    public List<String> getAllParamValuesV2(@QueryParam("sort") SortParams sortParams) {
        return sortParams.getSortParamList();
    }
}

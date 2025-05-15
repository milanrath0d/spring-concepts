package org.milan.resource;

import org.milan.model.SortParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * Custom resource for testing purpose
 *
 * @author Milan Rathod
 */
@Path("/custom")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class CustomResource {

    @Value("${test.msg: default msg}")
    private String message;

    @Value("${test.list}")
    private List<String> listValues;

    @Value("#{${test.map}}")
    private Map<String, String> mapValues;

    /**
     * For given request i.e. custom?sort=sort1&sort=sort2
     */
    @GET
    @Path("listParams")
    public List<String> getAllParamValues(@QueryParam("sort") List<String> sortOrders) {
        return sortOrders;
    }

    /**
     * For given request i.e. custom?sort=sort1,sort2,sort3
     */
    @GET
    @Path("beanParams")
    public List<String> getAllParamValuesV2(@QueryParam("sort") SortParams sortParams) {
        return sortParams.getSortParamList();
    }

    @GET
    @Path("message")
    public String testMessage() {
        return message + " " + listValues + " " + mapValues;
    }

}

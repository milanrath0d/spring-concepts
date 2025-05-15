package org.milan.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Example of {@link Invocation}
 *
 * @author Milan Rathod
 */
public class InvocationDemo {

    public static void main(String[] args) {
        InvocationDemo invocationDemo = new InvocationDemo();
        Invocation invocation = invocationDemo.prepareRequestForMessageByYear(2016);
        Response response = invocation.invoke();
        System.out.println(response.getStatus());
    }

    public Invocation prepareRequestForMessageByYear(int year) {
        Client client = ClientBuilder.newClient();

        return client.target("http://localhost:8080/Messenger/webapi")
            .path("messages")
            .queryParam("year", year)
            .request(MediaType.APPLICATION_JSON)
            .buildGet();
    }
}

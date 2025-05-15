package org.milan.client;

import org.milan.model.Message;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 * Generic Type demo
 *
 * @author Milan Rathod
 */
public class GenericDemo {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        List<Message> response = client.target("http://localhost:8080/api").
            path("messages").
            queryParam("year", 2016).
            request(MediaType.APPLICATION_JSON)
            .get(new GenericType<List<Message>>() {
            });
        System.out.println(response);
    }
}

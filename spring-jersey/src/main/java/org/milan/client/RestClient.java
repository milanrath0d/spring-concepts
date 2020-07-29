package org.milan.client;

import org.milan.model.Message;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Rest Client
 *
 * @author Milan Rathod
 */
public class RestClient {

    public static final String BASE_URL = "http://localhost:8080/api";

    public static void main(String[] args) {

        Client client = ClientBuilder.newClient();

        WebTarget baseTarget = client.target(BASE_URL);

        WebTarget messageTarget = baseTarget.path("messages");

        getMessage(messageTarget);

        postMessage(messageTarget);
    }

    private static void getMessage(WebTarget webTarget) {
        WebTarget singleMessageTarget = webTarget.path("{messageId}");

        Message message = singleMessageTarget
                .resolveTemplate("messageId", 2)
                .request(MediaType.APPLICATION_JSON)
                .get(Message.class);

        System.out.println(message);
    }

    private static void postMessage(WebTarget webTarget) {
        Message newMessage = new Message(4, "New Message", "deadhunter");

        Response postResponse = webTarget.request().post(Entity.json(newMessage));

        Message createdMessage = postResponse.readEntity(Message.class);

        System.out.println(createdMessage.getMessage());
    }

}

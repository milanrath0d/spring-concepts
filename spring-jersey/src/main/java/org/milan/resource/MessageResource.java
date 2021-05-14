package org.milan.resource;

import org.milan.model.Message;
import org.milan.model.MessageFilterBean;
import org.milan.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

/**
 * @author Milan Rathod
 */
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
public class MessageResource {

    @Autowired
    private MessageService messageService;

    @Autowired
    private CommentResource commentResource;

    @Autowired
    private LikeResource likeResource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getJsonMessages(@BeanParam MessageFilterBean messageFilterBean) {
        System.out.println("Json method call");
        if (messageFilterBean.getYear() > 0) {
            return messageService.getMessagesByYear(messageFilterBean.getYear());
        }
        if (messageFilterBean.getStart() >= 0 && messageFilterBean.getSize() > 0) {
            return messageService.getMessagesByPage(messageFilterBean.getStart(), messageFilterBean.getSize());
        }
        return messageService.getAllMessages();
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Message> getXmlMessages(@BeanParam MessageFilterBean messageFilterBean) {
        System.out.println("XML method call");
        if (messageFilterBean.getYear() > 0) {
            return messageService.getMessagesByYear(messageFilterBean.getYear());
        }
        if (messageFilterBean.getStart() >= 0 && messageFilterBean.getSize() > 0) {
            return messageService.getMessagesByPage(messageFilterBean.getStart(), messageFilterBean.getSize());
        }
        return messageService.getAllMessages();
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) {
        //ErrorMessage errorMessage=new ErrorMessage("Not found",404,"Error ");
        //Response response=Response.status(Status.NOT_FOUND).entity(errorMessage).build();
        Message message = messageService.getMessage(id);
        if (message == null) {
            //throw new WebApplicationException(response);
            throw new NotFoundException();
        }
        message.addLink(getUriForSelf(uriInfo, message), "self");
        message.addLink(getUriForProfile(uriInfo, message), "profile");
        message.addLink(getUriForComments(uriInfo, message), "comments");
        return message;
    }

    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) {
        Message newMessage = messageService.addMessage(message);
        //return Response.status(Status.ACCEPTED).entity(newMessage).build();
        String id = String.valueOf(newMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
        return Response.created(uri).
                entity(newMessage).build();
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long id, Message message) {
        message.setId(id);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") long id) {
        messageService.deleteMessage(id);
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return commentResource;
    }

    @Path("/{messageId}/likes")
    public LikeResource getLikeResource() {
        return likeResource;
    }

    private String getUriForProfile(UriInfo uriInfo, Message message) {
        String uri = uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).build().toString();
        return uri;
    }

    private String getUriForSelf(UriInfo uriInfo, Message message) {
        String uri = uriInfo.getBaseUriBuilder().path(MessageResource.class).path(Long.toString(message.getId())).build().toString();
        return uri;
    }

    private String getUriForComments(UriInfo uriInfo, Message message) {
        String uri = uriInfo.getBaseUriBuilder().path(MessageResource.class).
                path(MessageResource.class, "getCommentResource").
                path(CommentResource.class).resolveTemplate("messageId", message.getId()).build().toString();
        return uri;
    }
}

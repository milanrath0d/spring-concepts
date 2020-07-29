package org.milan.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement
@Data
@NoArgsConstructor
public class Message {

    private long id;

    private String message;

    private Date created;

    private String author;

    private Map<Long, Comment> comments = new HashMap<>();

    private List<Link> links = new ArrayList<>();

    @XmlTransient
    public Map<Long, Comment> getComments() {
        return comments;
    }

    public void addLink(String url, String rel) {
        Link link = new Link();
        link.setLink(url);
        link.setRel(rel);
        links.add(link);
    }

    public Message(long id, String message, String author) {
        super();
        this.id = id;
        this.message = message;
        this.author = author;
        this.created = new Date();
    }

}

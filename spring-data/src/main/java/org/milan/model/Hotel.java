package org.milan.model;

import lombok.Getter;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

/**
 * Hotel pojo
 *
 * @author Milan Rathod
 */
@Getter
@Document(collection = "Hotels")
public class Hotel {

    @Id
    private String id;

    private final String name;

    @Indexed(direction = IndexDirection.ASCENDING)
    private final int pricePerNight;

    private final Address address;

    private final List<Review> reviews;

    public Hotel(String name, int pricePerNight, Address address, List<Review> reviews) {
        this.name = name;
        this.pricePerNight = pricePerNight;
        this.address = address;
        this.reviews = reviews;
    }
}

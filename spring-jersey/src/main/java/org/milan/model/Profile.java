package org.milan.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Profile pojo
 *
 * @author Milan Rathod
 */
@XmlRootElement
@Data
@NoArgsConstructor
public class Profile {

    private long id;

    private String profileName;

    private String firstName;

    private String lastName;

    private Date created;

    public Profile(long id, String profileName, String firstName, String lastName) {
        this.id = id;
        this.profileName = profileName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.created = new Date();
    }

}

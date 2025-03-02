package org.milan.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * User pojo
 *
 * @author Milan Rathod
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;

    private String userName;

    private String password;

    private String roles;

    private boolean active;
}

package org.milan.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Milan Rathod
 */
@Data
@NoArgsConstructor
public class Comment {

    private long id;

    private String message;

    private Date created;

    private String author;

}

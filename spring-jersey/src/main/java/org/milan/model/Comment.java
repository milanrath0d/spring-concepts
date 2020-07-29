package org.milan.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Comment {

    private long id;

    private String message;

    private Date Created;

    private String author;

}

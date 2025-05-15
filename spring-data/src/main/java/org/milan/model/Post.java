package org.milan.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import java.util.Date;

/**
 * @author Milan Rathod
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@Document("posts")
public class Post {

    @Id
    private Long id;

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submissionDate;

    private String userName;

}
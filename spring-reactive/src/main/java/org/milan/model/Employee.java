package org.milan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Milan Rathod
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Employee {

    @Id
    private String id;

    private String name;

    private Long salary;
}

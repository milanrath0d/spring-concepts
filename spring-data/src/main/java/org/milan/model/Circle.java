package org.milan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Circle pojo
 *
 * @author Milan Rathod
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Circle {

    @Id
    private int id;

    private String name;
}

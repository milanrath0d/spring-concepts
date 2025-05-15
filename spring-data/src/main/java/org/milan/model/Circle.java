package org.milan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

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

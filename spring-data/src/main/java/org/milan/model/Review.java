package org.milan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Review pojo
 *
 * @author Milan Rathod
 */
@Getter
@AllArgsConstructor
public class Review {

    private String userName;

    private int rating;

    private boolean approved;
}

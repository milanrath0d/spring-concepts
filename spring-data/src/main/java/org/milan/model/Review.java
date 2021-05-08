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

    private final String userName;

    private final int rating;

    private final boolean approved;
}

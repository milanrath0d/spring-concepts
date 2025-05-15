package org.milan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Review model for hotel reviews
 *
 * @author Milan Rathod
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    
    private String userName;
    
    private int rating;
    
    private String comment;
}

package org.milan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Address model for location information
 *
 * @author Milan Rathod
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    
    private String city;
    
    private String country;
}

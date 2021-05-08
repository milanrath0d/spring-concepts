package org.milan.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Address Class
 *
 * @author Milan Rathod
 */
@Getter
@Setter
public class Address {

    /**
     * City
     */
    private String city;

    /**
     * State
     */
    private String state;

    /**
     * Pincode
     */
    private int pincode;

}

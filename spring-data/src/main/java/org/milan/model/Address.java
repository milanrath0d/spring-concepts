package org.milan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Address pojo
 *
 * @author Milan Rathod
 */
@Getter
@AllArgsConstructor
public class Address {

    private final String city;

    private final String country;
}

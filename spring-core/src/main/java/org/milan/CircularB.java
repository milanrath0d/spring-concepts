package org.milan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Add Description
 *
 * @author Milan Rathod
 */
@Component
public class CircularB {

    private CircularA circularA;

    private String message = "Hi";

    public CircularA getCircularA() {
        return circularA;
    }

    @Autowired
    public void setCircularA(CircularA circularA) {
        this.circularA = circularA;
    }

    public String getMessage() {
        return message;
    }
}


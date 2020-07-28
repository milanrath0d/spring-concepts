package org.milan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Add Description
 *
 * @author Milan Rathod
 */
@Component
public class CircularA {

    private CircularB circularB;

    public CircularB getCircularB() {
        return circularB;
    }

    @Autowired
    public void setCircularB(CircularB circularB) {
        this.circularB = circularB;
    }
}

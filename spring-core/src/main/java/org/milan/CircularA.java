package org.milan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Bean A related to circular dependency
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

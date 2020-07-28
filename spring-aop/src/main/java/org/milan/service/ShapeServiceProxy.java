package org.milan.service;

import org.milan.aspect.LoggingAspect;
import org.milan.model.Circle;

public class ShapeServiceProxy extends ShapeService {

    public Circle getCircle() {
        new LoggingAspect().test();
        return super.getCircle();
    }
}

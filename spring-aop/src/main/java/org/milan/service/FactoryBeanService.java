package org.milan.service;

import org.milan.model.Circle;
import org.milan.model.Triangle;

public class FactoryBeanService {

    public Object getBean(String beanType) {
        if (beanType.equals("shapeService")) return new ShapeServiceProxy();
        if (beanType.equals("circle")) return new Circle();
        if (beanType.equals("triange")) return new Triangle();
        return null;
    }
}

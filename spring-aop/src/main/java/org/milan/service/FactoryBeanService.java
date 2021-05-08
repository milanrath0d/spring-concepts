package org.milan.service;

import org.milan.model.Circle;
import org.milan.model.Triangle;

/**
 * @author Milan Rathod
 */
public class FactoryBeanService {

    public Object getBean(String beanType) {
        switch (beanType) {
            case "shapeService":
                return new ShapeServiceProxy();
            case "circle":
                return new Circle();
            case "triangle":
                return new Triangle();
            default:
                return null;
        }
    }
}

package org.milan.service;

import org.milan.model.Circle;
import org.milan.model.Triangle;

public class ShapeService {

    private Circle circle;

    private Triangle triangle;

    public Circle getCircle() {
        System.out.println("Shape Service Bean Called");
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Triangle getTriangle() {
        //System.out.println("Shape Service Bean Called");
        return triangle;
    }

    public void setTriangle(Triangle triangle) {
        this.triangle = triangle;
    }

}

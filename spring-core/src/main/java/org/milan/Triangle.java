package org.milan;

import org.milan.bean.Point;

import java.util.List;

/**
 * @author Milan Rathod
 */
public class Triangle implements Shape {

    private List<Point> points;

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void draw() {
        for (Point point : points) {
            System.out.println(point.getX() + " " + point.getY());
        }
    }

    public void myInit() {
        System.out.println("My InitializingBean");
    }

    public void myCleanUp() {
        System.out.println("My DisposableBean");
    }

}

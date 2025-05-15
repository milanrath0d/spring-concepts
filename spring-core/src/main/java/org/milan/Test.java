package org.milan;

import org.milan.bean.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import jakarta.annotation.Resource;

/**
 * @author Milan Rathod
 */
public class Test implements Shape {

    private Point center;

    @Autowired
    private MessageSource messageSource;

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public Point getCenter() {
        return center;
    }

    @Resource
    public void setCenter(Point center) {
        this.center = center;
    }

    @Override
    public void draw() {
        System.out.println(center.getX() + " " + center.getY());
        System.out.println(this.messageSource.getMessage("point", new Object[]{center.getX(), center.getY()}, "Default Greeting", null));
        //System.out.println(messageSource.getMessage("Greeting", null, "Default Greeting", null));
    }

}

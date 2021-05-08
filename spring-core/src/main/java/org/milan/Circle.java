package org.milan;

import org.milan.bean.Point;
import org.milan.event.DrawEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;

import javax.annotation.Resource;

/**
 * @author Milan Rathod
 */
public class Circle implements Shape, ApplicationEventPublisherAware {

    private Point point;

    private MessageSource messageSource;

    private ApplicationEventPublisher publisher;

    public MessageSource getMessageSource() {
        return messageSource;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public Point getPoint() {
        return point;
    }

    @Resource(name = "pointA")
    public void setPoint(Point point) {
        this.point = point;
    }

    @Override
    public void draw() {
        System.out.println("In a Circle");
        System.out.println(this.messageSource.getMessage("point", new Object[]{point.getX(), point.getY()}, "Default Greeting", null));
        //System.out.println(point.getX()+" "+point.getY());
        System.out.println(this.messageSource.getMessage("Greeting", null, "Default Greeting", null));
        DrawEvent event = new DrawEvent(this);
        publisher.publishEvent(event);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

}

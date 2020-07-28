package org.milan.event;

import org.springframework.context.ApplicationEvent;

/**
 * An {@link ApplicationEvent}
 *
 * @author Milan Rathod
 */
public class DrawEvent extends ApplicationEvent {

    public DrawEvent(Object source) {
        super(source);
    }

    public String toString() {
        return "Draw Event occurred";
    }

}

package org.milan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Starting point
 *
 * @author Milan Rathod
 */
@Component
public class DummyApp {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    @Qualifier("testClass")
    private TestInterface testInterface;

    public static void main(String[] args) {
        new DummyApp().print();
    }

    private void print() {
        TestInterface bean = applicationContext.getBean(TestInterface.class);
        bean.foo();
        //testInterface.foo();
    }
}

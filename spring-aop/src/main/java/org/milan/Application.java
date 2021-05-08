package org.milan;

import org.milan.service.impl.TestServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Milan Rathod
 */
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        TestServiceImpl testService = applicationContext.getBean("testService", TestServiceImpl.class);
        testService.test();
        /*FactoryBeanService factoryBean = new FactoryBeanService();
        ShapeService shapeService = (ShapeService) factoryBean.getBean("shapeService");
        shapeService.getCircle();*/
    }
}

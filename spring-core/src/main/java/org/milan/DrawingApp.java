package org.milan;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DrawingApp {

    public static void main(String[] args) {
        //Using BeanFactory
        //BeanFactory factory=new XmlBeanFactory(new FileSystemResource("spring.xml"));

        //Using ApplicationContext
		/*ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
		Triangle triangle=(Triangle)context.getBean("triangle");
		triangle.draw();*/

        //Using AbstractApplicationContext
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        context.registerShutdownHook();
        Shape shape = (Shape) context.getBean("circle");
        shape.draw();
    }

}

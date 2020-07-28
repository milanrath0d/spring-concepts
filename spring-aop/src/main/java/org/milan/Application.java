package org.milan;

import org.milan.service.FactoryBeanService;
import org.milan.service.ShapeService;

public class Application {

    public static void main(String[] args) {
		/*ApplicationContext ctx=new ClassPathXmlApplicationContext("spring.xml");
		ShapeService shapeService=ctx.getBean("shape", ShapeService.class);*/
        FactoryBeanService factoryBean = new FactoryBeanService();
        ShapeService shapeService = (ShapeService) factoryBean.getBean("shapeService");
        shapeService.getCircle();
        //System.out.println(shapeService.getCircle().getName());
        //shapeService.getCircle();
    }
}

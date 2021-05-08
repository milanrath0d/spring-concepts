package org.milan.processor;

import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author Milan Rathod
 */
public class BeanNamePostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("After Initialization " + beanName);
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("Before Initialization " + beanName);
        return bean;
    }

}

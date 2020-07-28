package org.milan;

import org.springframework.stereotype.Component;

/**
 * Add Description
 *
 * @author Milan Rathod
 */
@Component
public class TestClass implements TestInterface {

    @Override
    public void foo() {
        System.out.println("Test Class Method");
    }

}

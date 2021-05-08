package org.milan.model;

/**
 * Circle pojo
 *
 * @author Milan Rathod
 */
public class Circle {

    private String name;

    public String getName() {
        System.out.println("Getter of Circle called");
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("Setter get called");
        throw new RuntimeException();
    }

    public String setNameV2(String name) {
        this.name = name;
        System.out.println("Setter get called");
        return name;
    }
}

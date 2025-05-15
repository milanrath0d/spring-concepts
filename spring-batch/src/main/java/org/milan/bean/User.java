package org.milan.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * User pojo
 *
 * @author Milan Rathod
 */
@Entity
public class User {

    @Id
    private Integer id;

    private String name;

    private String dept;

    private Integer salary;

    public User() {
    }

    public User(Integer id, String name, String dept, Integer salary) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}

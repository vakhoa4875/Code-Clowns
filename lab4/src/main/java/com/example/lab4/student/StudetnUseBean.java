package com.example.lab4.student;

import org.springframework.stereotype.Component;

@Component
public class StudetnUseBean {
    private String name="Kha";
    private int age=19;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

package com.example.lab4.student;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentUseAnotiationBean {
    @Bean("anotiationBean")
    public StudentUseAnotiationBean studentUseAnotiationBean() {
        return new StudentUseAnotiationBean();
    }

    @Bean("anotiationBean2")
    public StudentUseBean studentUseBean() {
        StudentUseBean studentUseBean = new StudentUseBean();
        studentUseBean.setName("Hung");
        studentUseBean.setAge(19);
        return studentUseBean;
    }
}

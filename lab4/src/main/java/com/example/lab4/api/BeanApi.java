package com.example.lab4.api;

import com.example.lab4.student.StudentNotBean;
import com.example.lab4.student.StudentUseAnotiationBean;
import com.example.lab4.student.StudentUseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lab4")
public class BeanApi {
    // don't use the Bean
    private StudentNotBean studentNotBean;
    // use the Bean. If you don't use @Autowired for field. besides we use before constructor
    // if the project use @Bean or @Component , you should use the @Qualifier so is show priority
    private StudentUseBean studentUseBean;
    private StudentUseAnotiationBean studentUseAnotiationBean;

    @Autowired
    public BeanApi(@Qualifier("anotiationBean2") StudentUseBean studentUseBean
            , @Qualifier("anotiationBean") StudentUseAnotiationBean studentUseAnotiationBean) {
        this.studentUseAnotiationBean = studentUseAnotiationBean;
        this.studentUseBean = studentUseBean;
    }

    public BeanApi() {
        this.studentNotBean = new StudentNotBean();
    }

    @GetMapping("/notBean")
    public ResponseEntity<?> getInfnotBean() {
        Map<String, Object> result = new HashMap<>();
        result.put("Object", studentNotBean);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/useBean")
    public ResponseEntity<?> getInfUseBean() {
        Map<String, Object> result = new HashMap<>();
        result.put("Object", studentUseBean);
        result.put("Object2", studentUseAnotiationBean);
        return ResponseEntity.ok(result);
    }
}

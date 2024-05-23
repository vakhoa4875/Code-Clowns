package com.lab3.java5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/java5-b3/")
public class B3Controller {
    @GetMapping("/form-DataBinding")
    public String doGetFormDataBinding() {
        return "user/form/FormDataBindingValidation";
    }
}

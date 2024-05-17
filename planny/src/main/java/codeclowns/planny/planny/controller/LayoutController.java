package codeclowns.planny.planny.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LayoutController {
    @GetMapping("/bang")
    public String doGetBang(){
        return "user/layout/body/managerbang/bang";
    }

    @GetMapping("/home")
    public String doGetHome(){
        return "user/layout/body/home/home";
    }
}


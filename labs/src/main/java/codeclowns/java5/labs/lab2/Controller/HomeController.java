package codeclowns.java5.labs.lab2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/lab2")
public class HomeController {
    @GetMapping("/home")
    public String getHome(){
        return "lab2/user/hello";
    }

    @GetMapping("/user")
    public String getUser(){
        return "lab2/user/user";
    }
}

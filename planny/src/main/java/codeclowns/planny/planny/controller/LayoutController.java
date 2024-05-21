package codeclowns.planny.planny.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LayoutController {
    @GetMapping("/bang")
    public String doGetBang() {
        return "user/layout/body/managerbang/bang";
    }

    @GetMapping("/home")
    public String doGetHome() {
        return "user/layout/body/home/home";
    }

    @GetMapping("/board")
    public String test() {
        return "user/layout/body/singleBoard/board";
    }

    @GetMapping("/doimatkhau")
    public String doGetChangePass() {
        return "user/layout/body/accountmanager/changepassword";
    }
}


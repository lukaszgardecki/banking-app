package com.example.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }



//    @GetMapping("/error")
//    public String getError() {
//        return "error";
//    }

//    @GetMapping("/verify")
//    public String getVerify() {
//
//    }
}

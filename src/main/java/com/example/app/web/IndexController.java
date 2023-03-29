package com.example.app.web;

import com.example.app.exceptions.token.SessionHasExpiredException;
import com.example.app.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    private final UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }





//    @GetMapping("/error")
//    public String getError() {
//        return "error";
//    }

    @GetMapping("/verify")
    public String getVerify(@RequestParam("token") String token, @RequestParam("code") Integer code, Model model) {

        try {
            userService.verifyAccount(token, code);
        } catch (SessionHasExpiredException e) {
            model.addAttribute("error", e.getMessage());
            return "error/error";
        }

        model.addAttribute("successMsg", "Account verified successfully. Please proceed to log in!");
        return "login";
    }
}

package com.example.app.web;

import com.example.app.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/login-verify")
    public String checkVerification() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean userIsNotVerified = !userService.isUserVerified(username);
        if (userIsNotVerified) return "redirect:/logout?verified=false";
        return "redirect:/app/dashboard";
    }
}

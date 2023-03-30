package com.example.app.web;

import com.example.app.user.User;
import com.example.app.user.UserRepository;
import com.example.app.user.UserService;
import com.example.app.user.dto.UserDashboardDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

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

    @PostMapping("/login")
    public String process(@RequestParam("username") String username, Model model, HttpSession session) {
        boolean userVerified = userService.isUserVerified(username);
        if (userVerified) {
            userService.findUserByEmail(username)
                    .ifPresent(user -> session.setAttribute("user", user));
            return "redirect:/app/dashboard";
        }
        model.addAttribute("errorMsg", "This account is not verified yet. Please check email and verify your account.");
        return "login";
    }

}

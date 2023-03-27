package com.example.app;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String getRegister(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserDto user,
                           BindingResult result,
                           @RequestParam String confirm_password,
                           Model model) {


        if (result.hasErrors() && confirm_password.isEmpty()) {
            model.addAttribute("confirm_pass", "The confirm field is required");
            return "registration";
        } else if (result.hasErrors() && !confirm_password.equals(user.getPassword())) {
            model.addAttribute("confirm_pass", "Passwords must be the same");
            return "registration";
        } else if (!confirm_password.equals(user.getPassword())) {
            model.addAttribute("confirm_pass", "Passwords must be the same");
            return "registration";
        } else if (result.hasErrors()) {
            return "registration";
        }
        return "redirect:/";

    }
}

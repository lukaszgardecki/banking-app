package com.example.app.web;

import com.example.app.helpers.HTML;
import com.example.app.helpers.Token;
import com.example.app.mail.MailMessenger;
import com.example.app.user.UserDto;
import com.example.app.user.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

@Controller
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

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
                           Model model) throws MessagingException {
        String password = user.getPassword();

        if (result.hasErrors() && confirm_password.isEmpty()) {
            model.addAttribute("confirm_pass", "The confirm field is required");
            return "registration";
        } else if (result.hasErrors() && !confirm_password.equals(password)) {
            model.addAttribute("confirm_pass", "Passwords must be the same");
            return "registration";
        } else if (!confirm_password.equals(password)) {
            model.addAttribute("confirm_pass", "Passwords must be the same");
            return "registration";
        } else if (result.hasErrors()) {
            return "registration";
        }

        String token = Token.generateToken();
        user.setToken(token);
        Random random = new Random();
        int bound = 123;
        int code = bound * random.nextInt(bound);
        user.setCode(code);

        String emailBody = HTML.htmlEmailTemplate(token, code);
        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(hashed_password);

        userService.register(user);
        MailMessenger.sendEmail("alojz.kleks@company.com", user.getEmail(), "Verify Account", emailBody);
        String successMessage = "Account Registered Successfully. Please check your email and verify account!";
        model.addAttribute("successMsg", successMessage);
        return "registration";
    }
}

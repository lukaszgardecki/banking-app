package com.example.app.web;

import com.example.app.exceptions.form.ConfirmPassFieldIsEmpty;
import com.example.app.exceptions.form.MissingFormFieldsException;
import com.example.app.exceptions.form.PasswordsAreNotTheSameException;
import com.example.app.user.UserDto;
import com.example.app.user.UserService;
import jakarta.mail.MessagingException;
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

        try {
            userService.checkFormErrors(result, user, confirm_password);
        } catch (MissingFormFieldsException | PasswordsAreNotTheSameException | ConfirmPassFieldIsEmpty e) {
            model.addAttribute("formError", e.getMessage());
            return "registration";
        }

        UserDto registeredUser = userService.register(user);
        userService.sendVerifyEmailTo(registeredUser);

        String successMessage = "Account Registered Successfully. Please check your email and verify account!";
        model.addAttribute("successMsg", successMessage);
        return "registration";
    }

}

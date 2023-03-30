package com.example.app.web;

import com.example.app.account.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class AppController {
    private final AccountService accountService;

    public AppController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/dashboard")
    public String getDashboard() {
        return "dashboard";
    }
}

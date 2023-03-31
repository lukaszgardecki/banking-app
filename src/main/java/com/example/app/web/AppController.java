package com.example.app.web;

import com.example.app.account.Account;
import com.example.app.account.AccountService;
import com.example.app.user.dto.UserDashboardDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/app")
public class AppController {
    private final AccountService accountService;

    public AppController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/dashboard")
    public String getDashboard(HttpSession session) {
        UserDashboardDto user = (UserDashboardDto) session.getAttribute("user");
        if (user == null) return "redirect:/logout";
        Long id = user.getId();
        List<Account> accounts = accountService.getAccountsByUserId(id);
        BigDecimal totalBalance = accountService.getTotalBalance(id);
        session.setAttribute("userAccouts", accounts);
        session.setAttribute("totalBalance", totalBalance);

        return "dashboard";
    }
}

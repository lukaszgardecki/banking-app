package com.example.app.web;

import com.example.app.account.AccountService;
import com.example.app.account.dto.AccountDashboardDto;
import com.example.app.transact.payment.PaymentHistory;
import com.example.app.transact.payment.PaymentService;
import com.example.app.user.UserService;
import com.example.app.user.dto.UserDashboardDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/app")
public class AppController {
    private final AccountService accountService;
    private final PaymentService paymentService;
    private final UserService userService;

    public AppController(AccountService accountService, PaymentService paymentService, UserService userService) {
        this.accountService = accountService;
        this.paymentService = paymentService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String getDashboard(HttpSession session) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<UserDashboardDto> userOptional = userService.findUserByEmail(username);
        UserDashboardDto user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            return "redirect:/logout";
        }

        List<AccountDashboardDto> accounts = accountService.getAccountsByUserId(user.getId());
        BigDecimal totalBalance = accounts.stream()
                .map(AccountDashboardDto::getBalance)
                .reduce(new BigDecimal("0.00"),BigDecimal::add);
        session.setAttribute("user", user);
        session.setAttribute("userAccounts", accounts);
        session.setAttribute("totalBalance", totalBalance);
        return "dashboard";
    }

    @GetMapping("/payment-history")
    public String getPaymentHistory(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserDashboardDto> userOptional = userService.findUserByEmail(username);
        UserDashboardDto user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            return "redirect:/logout";
        }
        List<PaymentHistory> paymentHistory = paymentService.getPaymentRecordsById(user.getId());

        model.addAttribute("paymentHistory", paymentHistory);
        return "payment_history";
    }
}

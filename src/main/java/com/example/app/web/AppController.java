package com.example.app.web;

import com.example.app.account.AccountService;
import com.example.app.account.dto.AccountDashboardDto;
import com.example.app.transact.TransactHistory;
import com.example.app.transact.TransactService;
import com.example.app.transact.payment.PaymentHistory;
import com.example.app.transact.payment.PaymentService;
import com.example.app.user.UserService;
import com.example.app.user.dto.UserDashboardDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/app")
public class AppController {
    private final AccountService accountService;
    private final PaymentService paymentService;
    private final TransactService transactService;
    private final UserService userService;

    public AppController(AccountService accountService, PaymentService paymentService, TransactService transactService, UserService userService) {
        this.accountService = accountService;
        this.paymentService = paymentService;
        this.transactService = transactService;
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {
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
        model.addAttribute("user", user);
        model.addAttribute("userAccounts", accounts);
        model.addAttribute("totalBalance", totalBalance);
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

        List<PaymentHistory> paymentHistory = paymentService.getPaymentRecordsById(user.getId())
                .stream()
                .sorted(Comparator.comparing(e -> -e.getPaymentId()))
                .toList();

        model.addAttribute("user", user);
        model.addAttribute("paymentHistory", paymentHistory);
        return "payment_history";
    }

    @GetMapping("/transact-history")
    public String getTransactionHistory(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserDashboardDto> userOptional = userService.findUserByEmail(username);
        UserDashboardDto user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            return "redirect:/logout";
        }
        List<TransactHistory> transactHistory = transactService.getTransactionHistoryByUserId(user.getId())
                .stream()
                .sorted(Comparator.comparingLong(transactHist -> -transactHist.getTransactionId()))
                .toList();

        model.addAttribute("user", user);
        model.addAttribute("transactHistory", transactHistory);
        return "transact_history";
    }

}

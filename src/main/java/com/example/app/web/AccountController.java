package com.example.app.web;

import com.example.app.account.AccountService;
import com.example.app.user.dto.UserDashboardDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create-account")
    public String createAccount(@RequestParam("account_name") String accountName,
                                @RequestParam("account_type") String accountType,
                                RedirectAttributes redirectAttributes,
                                HttpSession session) {
        if (accountName == null || accountName.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMsg", "Account Name cannot be empty!");
            return "redirect:/app/dashboard";
        } else if (accountType == null || accountType.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMsg", "Account Type cannot be empty!");
            return "redirect:/app/dashboard";
        }

        UserDashboardDto user = (UserDashboardDto) session.getAttribute("user");
        Long userId = user.getId();
        String accountNumber = accountService.generateAccountNumber();
        accountService.createAccount(userId, accountNumber, accountName, accountType);
        redirectAttributes.addFlashAttribute("successMsg", "Account has been successfully created");
        return "redirect:/app/dashboard";
    }
}

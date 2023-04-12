package com.example.app.web;

import com.example.app.account.AccountService;
import com.example.app.account.dto.AccountDto;
import com.example.app.user.UserService;
import com.example.app.user.dto.UserDashboardDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final UserService userService;

    public AccountController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @PostMapping("/create-account")
    public String createAccount(@RequestParam("account_name") String accountName,
                                @RequestParam("account_type") String accountType,
                                RedirectAttributes redirectAttributes) {
        if (accountName == null || accountName.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMsg", "Account Name cannot be empty!");
            return "redirect:/app/dashboard";
        } else if (accountType == null || accountType.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMsg", "Account Type cannot be empty!");
            return "redirect:/app/dashboard";
        }

        UserDashboardDto user = userService.getLoggedInUser().get();
        Long userId = user.getId();

        AccountDto account = new AccountDto(userId, accountName, accountType);
        accountService.createAccount(account);
        redirectAttributes.addFlashAttribute("successMsg", "Account has been successfully created");
        return "redirect:/app/dashboard";
    }
}

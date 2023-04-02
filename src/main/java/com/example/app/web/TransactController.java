package com.example.app.web;

import com.example.app.account.AccountService;
import com.example.app.account.dto.AccountDashboardDto;
import com.example.app.exceptions.form.EmptyFieldException;
import com.example.app.transact.DepositTransactForm;
import com.example.app.transact.TransactForm;
import com.example.app.transact.TransactService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/transact")
public class TransactController {
    private final TransactService transactService;
    private final AccountService accountService;

    public TransactController(TransactService transactService, AccountService accountService) {
        this.transactService = transactService;
        this.accountService = accountService;
    }

    @PostMapping("/deposit")
    String deposit(@RequestParam("deposit_amount") String depositAmountStr,
               @RequestParam("account_id") String accountId,
               HttpSession session,
               RedirectAttributes attributes) {

        TransactForm depositTransactForm = new DepositTransactForm(depositAmountStr, accountId);

        boolean valuesAreNotCorrect = !validateFields(depositTransactForm, attributes);
        if (valuesAreNotCorrect) return "redirect:/app/dashboard";

        BigDecimal depositAmount = new BigDecimal(depositAmountStr);
        List<AccountDashboardDto> userAccounts = (List<AccountDashboardDto>) session.getAttribute("userAccounts");
        BigDecimal accountBalance = accountService.getAccountBalance(accountId, userAccounts);
        BigDecimal newBalance = accountBalance.add(depositAmount);

        accountService.changeAccountBalance(newBalance, Long.parseLong(accountId));
        attributes.addFlashAttribute("successMsg", "Account deposited successfully");
        return "redirect:/app/dashboard";
    }

    private BigDecimal getAccountBalance(String accountId, List<AccountDashboardDto> userAccounts) {
        return userAccounts.stream()
                .filter(acc -> String.valueOf(acc.getId()).equals(accountId))
                .map(AccountDashboardDto::getBalance)
                .findFirst().get();
    }

    private boolean validateFields(TransactForm transactForm, RedirectAttributes attributes) {
        try {
            transactService.validateForm(transactForm);
        } catch (EmptyFieldException | NumberFormatException e) {
            attributes.addFlashAttribute("errorMsg", e.getMessage());
            return false;
        }
        return true;
    }

}

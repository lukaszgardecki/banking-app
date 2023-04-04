package com.example.app.web;

import com.example.app.account.AccountService;
import com.example.app.exceptions.form.EmptyFieldException;
import com.example.app.exceptions.form.SameAccountsFieldsException;
import com.example.app.exceptions.transact.TooLowBalanceException;
import com.example.app.helpers.Message;
import com.example.app.transact.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    String deposit(@RequestParam("deposit_amount") String amount,
               @RequestParam("account_id") String accountTo,
               RedirectAttributes attributes) {

        DepositTransactForm form = new DepositTransactForm(amount, accountTo);
        boolean formIsIncorrect = validateFormFields(form, attributes);
        if (formIsIncorrect) return "redirect:/app/dashboard";

        accountService.depositMoney(form);

        attributes.addFlashAttribute("successMsg", Message.DEPOSIT_SUCCESS);
        return "redirect:/app/dashboard";
    }

    @PostMapping("/transfer")
    String transfer(@RequestParam("transfer_from") String accountFrom,
                    @RequestParam("transfer_to") String accountTo,
                    @RequestParam("transfer_amount") String amount,
                    RedirectAttributes attributes) {

        TransferTransactForm form  = new TransferTransactForm(amount, accountFrom, accountTo);
        boolean formIsIncorrect = validateFormFields(form, attributes);
        if (formIsIncorrect) return "redirect:/app/dashboard";

        boolean transferFailed = doTransfer(form, attributes);
        if (transferFailed) return "redirect:/app/dashboard";

        attributes.addFlashAttribute("successMsg", Message.TRANSFER_SUCCESS);
        return "redirect:/app/dashboard";
    }


    @PostMapping("/withdraw")
    String withdraw(@RequestParam("withdrawal_amount") String amount,
                   @RequestParam("account_id") String accountFrom,
                   RedirectAttributes attributes) {

        WithdrawTransactForm form = new WithdrawTransactForm(amount, accountFrom);
        boolean formIsIncorrect = validateFormFields(form, attributes);
        if (formIsIncorrect) return "redirect:/app/dashboard";

        boolean transferFailed = doWithdraw(form, attributes);
        if (transferFailed) return "redirect:/app/dashboard";

        attributes.addFlashAttribute("successMsg", String.format(Message.WITHDRAW_SUCCESS, form.getAmount()));
        return "redirect:/app/dashboard";
    }

    private boolean doWithdraw(WithdrawTransactForm form, HttpSession session, RedirectAttributes attributes) {
        try {
            accountService.withdrawMoney(form);
        } catch (TooLowBalanceException e) {
            attributes.addFlashAttribute("errorMsg", e.getMessage());
            return true;
        }
        return false;
    }


    private boolean doTransfer(TransferTransactForm form, RedirectAttributes attributes) {
        try {
            accountService.transferMoney(form);
        } catch (TooLowBalanceException e) {
            attributes.addFlashAttribute("errorMsg", e.getMessage());
            return true;
        }
        return false;
    }


    // TODO: 03.04.2023 change form validation to custom validator and annotations!
    private boolean validateFormFields(TransactForm transactForm, RedirectAttributes attributes) {
        try {
            transactService.validateForm(transactForm);
        } catch (EmptyFieldException | NumberFormatException | SameAccountsFieldsException e) {
            attributes.addFlashAttribute("errorMsg", e.getMessage());
            return true;
        }
        return false;
    }

}

package com.example.app.web;

import com.example.app.account.AccountService;
import com.example.app.exceptions.form.EmptyFieldException;
import com.example.app.exceptions.form.SameAccountsFieldsException;
import com.example.app.exceptions.transact.TooLowBalanceException;
import com.example.app.helpers.Message;
import com.example.app.transact.DepositTransactForm;
import com.example.app.transact.TransactForm;
import com.example.app.transact.TransactService;
import com.example.app.transact.TransferTransactForm;
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
               HttpSession session,
               RedirectAttributes attributes) {

        DepositTransactForm form = new DepositTransactForm(amount, accountTo);
        boolean formIsIncorrect = validateFormFields(form, attributes);
        if (formIsIncorrect) return "redirect:/app/dashboard";

        accountService.doDeposit(form, session);

        attributes.addFlashAttribute("successMsg", Message.DEPOSIT_SUCCESS);
        return "redirect:/app/dashboard";
    }

    @PostMapping("/transfer")
    String transfer(@RequestParam("transfer_from") String accountFrom,
                    @RequestParam("transfer_to") String accountTo,
                    @RequestParam("transfer_amount") String amount,
                    HttpSession session,
                    RedirectAttributes attributes) {

        TransferTransactForm form  = new TransferTransactForm(amount, accountFrom, accountTo);
        boolean formIsIncorrect = validateFormFields(form, attributes);
        if (formIsIncorrect) return "redirect:/app/dashboard";

        boolean transferFailed = doTransfer(form, session, attributes);
        if (transferFailed) return "redirect:/app/dashboard";

        attributes.addFlashAttribute("successMsg", Message.TRANSFER_SUCCESS);
        return "redirect:/app/dashboard";
    }

    private boolean doTransfer(TransferTransactForm form, HttpSession session, RedirectAttributes attributes) {
        try {
            accountService.doTransfer(form, session);
        } catch (TooLowBalanceException e) {
            attributes.addFlashAttribute("errorMsg", e.getMessage());
            return true;
        }
        return false;
    }


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

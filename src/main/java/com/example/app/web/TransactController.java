package com.example.app.web;

import com.example.app.account.AccountService;
import com.example.app.exceptions.form.EmptyFieldException;
import com.example.app.exceptions.form.SameAccountsFieldsException;
import com.example.app.exceptions.transact.TooLowBalanceException;
import com.example.app.helpers.Message;
import com.example.app.transact.TransactDto;
import com.example.app.transact.TransactService;
import com.example.app.transact.forms.*;
import com.example.app.transact.payment.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/transact")
public class TransactController {
    private final TransactService transactService;
    private final AccountService accountService;
    private final PaymentService paymentService;


    public TransactController(TransactService transactService, AccountService accountService, PaymentService paymentService) {
        this.transactService = transactService;
        this.accountService = accountService;
        this.paymentService = paymentService;
    }


    @PostMapping("/deposit")
    String deposit(DepositTransactForm form, RedirectAttributes attributes) {

        boolean formIsIncorrect = validateFormFields(form, attributes);
        if (formIsIncorrect) return "redirect:/app/dashboard";

        TransactDto transact = accountService.depositMoney(form);
        transactService.prepareSuccessTransact(transact, form);
        transactService.logTransaction(transact);

        attributes.addFlashAttribute("successMsg", Message.DEPOSIT_SUCCESS);
        return "redirect:/app/dashboard";
    }


    @PostMapping("/transfer")
    String transfer(TransferTransactForm form, RedirectAttributes attributes) {

        boolean formIsIncorrect = validateFormFields(form, attributes);
        if (formIsIncorrect) return "redirect:/app/dashboard";

        boolean transferFailed = doTransfer(form, attributes);
        if (transferFailed) return "redirect:/app/dashboard";

        attributes.addFlashAttribute("successMsg", Message.TRANSFER_SUCCESS);
        return "redirect:/app/dashboard";
    }


    @PostMapping("/withdraw")
    String withdraw(WithdrawTransactForm form, RedirectAttributes attributes) {

        boolean formIsIncorrect = validateFormFields(form, attributes);
        if (formIsIncorrect) return "redirect:/app/dashboard";

        boolean transferFailed = doWithdraw(form, attributes);
        if (transferFailed) return "redirect:/app/dashboard";

        attributes.addFlashAttribute("successMsg", String.format(Message.WITHDRAW_SUCCESS, form.getAmount()));
        return "redirect:/app/dashboard";
    }

    @PostMapping("/payment")
    String payment(PaymentTransactForm form, RedirectAttributes attributes) {

        boolean formIsIncorrect = validateFormFields(form, attributes);
        if (formIsIncorrect) return "redirect:/app/dashboard";

        boolean paymentFailed = doPayment(form, attributes);
        if (paymentFailed) return "redirect:/app/dashboard";

        attributes.addFlashAttribute("successMsg", String.format(Message.PAYMENT_CODE_SUCCESS, form.getAmount()));
        return "redirect:/app/dashboard";
    }


    private boolean doPayment(PaymentTransactForm form, RedirectAttributes attributes) {
        try {
            TransactDto transact = accountService.withdrawMoney(form);
            transactService.prepareSuccessTransact(transact, form);
            transactService.logTransaction(transact);
            paymentService.makePayment(transact, form);
        } catch (TooLowBalanceException e) {
            TransactDto failTransact = new TransactDto();
            transactService.prepareFailedTransact(failTransact, form);
            transactService.logTransaction(failTransact);
            paymentService.makePayment(failTransact, form);
            attributes.addFlashAttribute("errorMsg", e.getMessage());
            return true;
        }
        return false;
    }


    private boolean doWithdraw(WithdrawTransactForm form, RedirectAttributes attributes) {
        try {
            TransactDto transact = accountService.withdrawMoney(form);
            transactService.prepareSuccessTransact(transact, form);
            transactService.logTransaction(transact);
        } catch (TooLowBalanceException e) {
            TransactDto failTransact = new TransactDto();
            transactService.prepareFailedTransact(failTransact, form);
            transactService.logTransaction(failTransact);
            attributes.addFlashAttribute("errorMsg", e.getMessage());
            return true;
        }
        return false;
    }


    private boolean doTransfer(TransferTransactForm form, RedirectAttributes attributes) {

        try {
            accountService.transferMoney(form).forEach(transact -> {
                transactService.prepareSuccessTransact(transact, form);
                transactService.logTransaction(transact);
            });
        } catch (TooLowBalanceException e) {
            TransactDto failTransact = new TransactDto();
            transactService.prepareFailedTransact(failTransact, form);
            transactService.logTransaction(failTransact);
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

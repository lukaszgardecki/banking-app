package com.example.app.transact;

import lombok.Getter;

@Getter
public class WithdrawTransactForm extends TransactForm {
    private String accountFrom;

    public WithdrawTransactForm(String amount, String accountFrom) {
        super(amount);
        this.accountFrom = accountFrom;
    }
}

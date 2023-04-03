package com.example.app.transact;

import lombok.Getter;

@Getter
public class DepositTransactForm extends TransactForm {
    private String accountTo;

    public DepositTransactForm(String amount, String accountTo) {
        super(amount);
        this.accountTo = accountTo;
    }
}

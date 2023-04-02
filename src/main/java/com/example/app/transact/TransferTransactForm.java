package com.example.app.transact;

import lombok.Getter;

@Getter
public class TransferTransactForm extends TransactForm {
    private String accountFrom;
    private String accountTo;

    public TransferTransactForm(String amount, String accountFrom, String accountTo) {
        super(amount);
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
    }
}
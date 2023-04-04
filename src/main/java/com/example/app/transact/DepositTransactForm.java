package com.example.app.transact;

import lombok.Getter;

@Getter
public class DepositTransactForm extends TransactForm {

    public DepositTransactForm(String amount, String accountTo) {
        super(amount, accountTo);
    }
}

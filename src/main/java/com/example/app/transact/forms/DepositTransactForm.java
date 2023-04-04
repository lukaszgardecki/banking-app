package com.example.app.transact.forms;

import lombok.Getter;

@Getter
public class DepositTransactForm extends TransactForm {

    public DepositTransactForm(String amount, String accountTo) {
        super(amount, accountTo);
    }
}

package com.example.app.transact.forms;

import lombok.Getter;

@Getter
public class WithdrawTransactForm extends TransactForm {

    public WithdrawTransactForm(String amount, String accountFrom) {
        super(amount, accountFrom);
    }
}

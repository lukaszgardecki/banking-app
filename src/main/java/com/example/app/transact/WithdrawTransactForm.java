package com.example.app.transact;

import lombok.Getter;

@Getter
public class WithdrawTransactForm extends TransactForm {

    public WithdrawTransactForm(String amount, String accountFrom) {
        super(amount, accountFrom);
    }
}

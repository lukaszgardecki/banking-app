package com.example.app.transact.forms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawTransactForm extends TransactForm {
    private String amount;
    private String accountFromId;

    public WithdrawTransactForm(String amount, String accountFromId) {
        this.amount = amount;
        this.accountFromId = accountFromId;
        super.setAmount(amount);
        super.setAccountFromId(accountFromId);
    }
}

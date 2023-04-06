package com.example.app.transact.forms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositTransactForm extends TransactForm {
    private String amount;
    private String accountToId;

    public DepositTransactForm(String amount, String accountToId) {
        this.amount = amount;
        this.accountToId = accountToId;
        super.setAmount(amount);
        super.setAccountToId(accountToId);
    }
}

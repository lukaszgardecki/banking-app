package com.example.app.transact.forms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferTransactForm extends TransactForm {
    private String amount;
    private String accountFromId;
    private String accountToId;

    public TransferTransactForm(String amount, String accountFromId, String accountToId) {
        this.amount = amount;
        this.accountFromId = accountFromId;
        this.accountToId = accountToId;
        super.setAmount(amount);
        super.setAccountFromId(accountFromId);
        super.setAccountToId(accountToId);
    }
}

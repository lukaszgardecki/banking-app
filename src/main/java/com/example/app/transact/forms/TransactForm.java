package com.example.app.transact.forms;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class TransactForm {
    private String amount;
    private String accountFromId;
    private String accountToId;
    private String beneficiary;
    private String accountToNumber;
    private String reference;
}


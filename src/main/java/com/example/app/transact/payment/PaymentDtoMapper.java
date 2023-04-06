package com.example.app.transact.payment;

import com.example.app.transact.forms.PaymentTransactForm;

import java.math.BigDecimal;

public class PaymentDtoMapper {

    public static Payment map(PaymentTransactForm form) {
        Payment payment = new Payment();
        payment.setAccountFromId(Long.parseLong(form.getAccountFromId()));
        payment.setBeneficiary(form.getBeneficiary());
        payment.setBeneficiaryAccNo(form.getBeneficiaryAccNo());
        payment.setAmount(new BigDecimal(form.getAmount()));
        payment.setReference(form.getReference());
        return payment;
    }
}

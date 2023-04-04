package com.example.app.transact.payment;

import com.example.app.helpers.Message;
import com.example.app.transact.PaymentTransactForm;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void makePayment(PaymentTransactForm transactForm) {
        Long accountId = Long.parseLong(transactForm.getAccount());
        String beneficiary = transactForm.getBeneficiary();
        String accountNumber = transactForm.getAccountNumber();
        BigDecimal amount = new BigDecimal(transactForm.getAmount());
        String reference = transactForm.getReference();
        String status = "success";
        String reasonCode = Message.PAYMENT_SUCCESS;
        LocalDateTime createdAt = LocalDateTime.now();

        paymentRepository.makePayment(accountId, beneficiary, accountNumber, amount,
                reference, status, reasonCode, createdAt);
    }
}

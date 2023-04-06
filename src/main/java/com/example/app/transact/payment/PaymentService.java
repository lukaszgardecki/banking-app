package com.example.app.transact.payment;

import com.example.app.transact.TransactDto;
import com.example.app.transact.forms.PaymentTransactForm;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void makePayment(TransactDto transact, PaymentTransactForm transactForm) {
        Payment paymentToSave = PaymentDtoMapper.map(transactForm);
        paymentToSave.setStatus(transact.getStatus());
        paymentToSave.setReasonCode(transact.getReasonCode());
        paymentToSave.setCreatedAt(transact.getCreatedAt());
        paymentRepository.save(paymentToSave);
    }
}

package com.example.app.transact.payment;

import com.example.app.transact.TransactDto;
import com.example.app.transact.forms.PaymentTransactForm;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;

    public PaymentService(PaymentRepository paymentRepository, PaymentHistoryRepository paymentHistoryRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentHistoryRepository = paymentHistoryRepository;
    }

    public void makePayment(TransactDto transact, PaymentTransactForm transactForm) {
        Payment paymentToSave = PaymentDtoMapper.map(transactForm);
        paymentToSave.setAmount(new BigDecimal(transact.getAmount()));
        paymentToSave.setCurrency("PLN");
        paymentToSave.setStatus(transact.getStatus());
        paymentToSave.setReasonCode(transact.getReasonCode());
        paymentToSave.setCreatedAt(transact.getCreatedAt());
        paymentRepository.save(paymentToSave);
    }

    public List<PaymentHistory> getPaymentRecordsById(Long id) {
        return paymentHistoryRepository.findAllByUserId(id);
    }

}

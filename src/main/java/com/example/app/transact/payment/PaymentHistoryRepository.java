package com.example.app.transact.payment;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentHistoryRepository extends CrudRepository<PaymentHistory, Long> {

    List<PaymentHistory> findAllByAccountFromId(Long id);
}

package com.example.app.transact;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactHistoryRepository extends CrudRepository<TransactHistory, Long> {

    List<TransactHistory> findAllByUserId(Long userId);
}

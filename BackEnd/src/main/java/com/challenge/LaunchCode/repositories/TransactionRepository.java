package com.challenge.LaunchCode.repositories;

import com.challenge.LaunchCode.models.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    List<Transaction> findByUserId(int userId);
}

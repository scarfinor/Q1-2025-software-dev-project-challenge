package com.challenge.LaunchCode.repositories;

import com.challenge.LaunchCode.models.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}

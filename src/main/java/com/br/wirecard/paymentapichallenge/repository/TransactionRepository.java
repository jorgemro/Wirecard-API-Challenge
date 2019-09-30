package com.br.wirecard.paymentapichallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.wirecard.paymentapichallenge.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}

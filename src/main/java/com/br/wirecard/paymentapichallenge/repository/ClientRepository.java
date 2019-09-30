package com.br.wirecard.paymentapichallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.wirecard.paymentapichallenge.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}

package com.br.wirecard.paymentapichallenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.wirecard.paymentapichallenge.model.Buyer;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> { 
	
	public static final String FIND_BY_CPF = "SELECT * FROM BUYER WHERE BUYER_CPF = ?1";
	
	@Query(value = FIND_BY_CPF, nativeQuery = true)
	public Optional<Buyer> findByCpf(String cpf);
}

package com.br.wirecard.paymentapichallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.wirecard.paymentapichallenge.model.Boleto;
import com.br.wirecard.paymentapichallenge.model.Payment;
import com.br.wirecard.paymentapichallenge.model.Transaction;
import com.br.wirecard.paymentapichallenge.repository.BuyerRepository;
import com.br.wirecard.paymentapichallenge.repository.ClientRepository;
import com.br.wirecard.paymentapichallenge.repository.TransactionRepository;
import com.br.wirecard.paymentapichallenge.exception.ResourceNotFoundException;;

@RestController
public class PaymentController {

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private BuyerRepository buyerRepository;

	@PostMapping(value = "/payments")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Transaction createPaymentTransaction(@RequestBody Transaction transaction) {
		transaction.setMessage("Pagamento processado com sucesso.");
		transaction.setSuccessfully(true);

		clientRepository.findById(transaction.getClient().getClientId()).map(client -> {
			transaction.setClient(client);
			return client;
		});

		if (!transaction.getBuyer().getCpf().isEmpty()) {
			buyerRepository.findByCpf(transaction.getBuyer().getCpf()).map(buyer -> {
				buyer.setName(transaction.getBuyer().getName());
				buyer.setEmail(transaction.getBuyer().getEmail());
				transaction.setBuyer(buyer);

				return buyer;
			});
		}

		if (transaction.getPayment().getType() == Payment.PaymentType.Boleto) {
			transaction.getPayment().setBoleto(new Boleto(transaction.getPayment().getAmount()));
			transaction.getPayment().setCard(null);
		} else if (transaction.getPayment().getType() == Payment.PaymentType.CreditCard) {
			transaction.getPayment().getCard().validateCard();

			if (transaction.getPayment().getCard().isValid() == false) {
				transaction.setSuccessfully(transaction.getPayment().getCard().isValid());
				transaction.setMessage(transaction.getPayment().getCard().getMessage());
			}
		}

		return transactionRepository.save(transaction);
	}

	@GetMapping(value = "/payments")
	public List<Transaction> getAllPayments() {
		return transactionRepository.findAll();
	}

	@GetMapping(value = "/payments/{paymentId}")
	public Transaction getPaymentById(@PathVariable int paymentId) {
		return transactionRepository.findById(paymentId).orElseThrow(
				() -> new ResourceNotFoundException("Transaction [paymentId=" + paymentId + "] can't be found"));
	}

}

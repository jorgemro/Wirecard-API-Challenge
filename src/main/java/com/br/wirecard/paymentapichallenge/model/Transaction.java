package com.br.wirecard.paymentapichallenge.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "TRANSACTION")
@Entity(name = "Transaction")
public class Transaction {

	private int transactionId;
	private boolean successful;
	private String message;
	private Client client;
	private Buyer buyer;
	private Payment payment;

	@Id
	@GeneratedValue
	@Column(name = "TRANSACTION_ID")
	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	@Column(name = "TRANSACTION_SUCCESSFUL")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public boolean isSuccessfully() {
		return successful;
	}

	public void setSuccessfully(boolean successfully) {
		this.successful = successfully;
	}

	@Column(name = "TRANSACTION_MESSAGE")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "clientId", referencedColumnName = "CLIENT_ID")
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "buyerId", referencedColumnName = "BUYER_ID")
	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PaymentId", referencedColumnName = "PAYMENT_ID")
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}

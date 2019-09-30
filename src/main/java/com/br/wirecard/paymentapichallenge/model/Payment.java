package com.br.wirecard.paymentapichallenge.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.br.wirecard.paymentapichallenge.resource.PaymentTypeJpaConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "PAYMENT")
@Entity
public class Payment {
	private int paymentId;
	private double amount;
	private PaymentType type;
	private Card card;
	private Boleto boleto;

	// Valid types of payment
	public enum PaymentType {
		Boleto, CreditCard
	}

	@Id
	@GeneratedValue
	@Column(name = "PAYMENT_ID")
	@JsonIgnore
	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	@Column(name = "PAYMENT_AMOUNT")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name = "PAYMENT_TYPE")
	@Convert(converter = PaymentTypeJpaConverter.class)
	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CardId", referencedColumnName = "CARD_ID")
	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BoletoId", referencedColumnName = "BOLETO_ID")
	public Boleto getBoleto() {
		return boleto;
	}

	public void setBoleto(Boleto boleto) {
		this.boleto = boleto;
	}
}

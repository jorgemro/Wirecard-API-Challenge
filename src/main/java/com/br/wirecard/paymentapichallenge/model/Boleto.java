package com.br.wirecard.paymentapichallenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "BOLETO")
@Entity
public class Boleto {

	private int boletoId;
	private String boletoNumber;

	public Boleto() {

	}

	public Boleto(double amount) {
		this.boletoNumber = generateBoletoNumber(amount);
	}

	private String generateBoletoNumber(double amount) {
		String amountString = String.format("%.2f", amount).replace(".", "").replace(",", "");
		for (int i = amountString.length(); i <= 10; i++) {
			amountString = "0" + amountString;
		}

		return "34191.79001 01043.510047 91020.150008 5 8" + amountString;
	}

	@Id
	@GeneratedValue
	@Column(name = "BOLETO_ID")
	@JsonIgnore
	public int getBoletoId() {
		return boletoId;
	}

	public void setBoletoId(int boletoId) {
		this.boletoId = boletoId;
	}

	@Column(name = "BOLETO_NUMBER")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public String getBoletoNumber() {
		return boletoNumber;
	}

	public void setBoletoNumber(String boletoNumber) {
		this.boletoNumber = boletoNumber;
	}

}

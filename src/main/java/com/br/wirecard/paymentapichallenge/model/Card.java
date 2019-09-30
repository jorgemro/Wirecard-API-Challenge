package com.br.wirecard.paymentapichallenge.model;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "CARD")
@Entity
public class Card {

	private int cardId;
	private String cardIssuer;
	private String holderName;
	private String cardNumber;
	private Date expirationDate;
	private int cardCVV;
	private boolean isValid;
	private String message;

	public void validateCard() {
		this.isValid = true;
		this.message = "";

		if (this.expirationDate.compareTo(new Date()) < 0) {
			this.isValid = false;
			this.message = "Data de validade do cartão não é valida.";
			return;
		}

		if (this.cardNumber.isEmpty()) {
			this.isValid = false;
			this.message = "Numero de cartão esta vazio.";
			return;
		}

		this.cardNumber = this.cardNumber.replace(" ", "");

		String digit1 = this.cardNumber.substring(0, 1);
		String digit2 = this.cardNumber.substring(0, 2);
		String digit3 = this.cardNumber.substring(0, 3);

		if (digit1.equals("4")) {
			this.cardIssuer = "VISA";
			if (this.cardNumber.length() != 13 || this.cardNumber.length() != 16) {
				this.isValid = false;
				this.message = "Numero de cartão invalido para bandeira VISA.";
				return;
			}

		} else if (digit2.compareTo("51") >= 0 && digit2.compareTo("55") <= 0) {
			this.cardIssuer = "MASTERCARD";
			if (this.cardNumber.length() != 16) {
				this.isValid = false;
				this.message = "Numero de cartão invalido para bandeira MASTERCARD.";
				return;
			}
		} else if (digit2.equals("34") || digit2.equals("37")) {
			this.cardIssuer = "AMERICAN EXPRESS";
			if (this.cardNumber.length() != 15) {
				this.isValid = false;
				this.message = "Numero de cartão invalido para bandeira AMERICAN EXPRESS.";
				return;
			}
		} else if (digit2.equals("36") || digit2.equals("38")
				|| (digit3.compareTo("300") >= 0 && digit3.compareTo("305") <= 0)) {
			this.cardIssuer = "DINERS CLUB";
			if (this.cardNumber.length() != 14) {
				this.isValid = false;
				this.message = "Numero de cartão invalido para bandeira DINNERS CLUB.";
				return;
			}
		} else {
			this.isValid = false;
			this.message = "Numero de cartão invalido.";
			return;
		}
	}

	@Id
	@GeneratedValue
	@Column(name = "CARD_ID")
	@JsonIgnore
	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	@Column(name = "CARD_ISSUER")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public String getCardIssuer() {
		return cardIssuer;
	}

	public void setCardIssuer(String cardIssuer) {
		this.cardIssuer = cardIssuer;
	}

	@Column(name = "CARD_HOLDER_NAME")
	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	@Column(name = "CARD_NUMBER")
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT-3")
	@Column(name = "EXPIRATION_DATE")
	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Column(name = "CARD_CVV")
	public int getCardCVV() {
		return cardCVV;
	}

	public void setCardCVV(int cardCVV) {
		this.cardCVV = cardCVV;
	}

	@JsonIgnore
	@Transient
	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	@JsonIgnore
	@Transient
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

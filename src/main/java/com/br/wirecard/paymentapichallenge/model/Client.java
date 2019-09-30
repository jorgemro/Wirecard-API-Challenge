package com.br.wirecard.paymentapichallenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "CLIENT")
@Entity(name = "Client")
public class Client {

	private int clientId;

	@Id
	@Column(name = "CLIENT_ID")
	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
}

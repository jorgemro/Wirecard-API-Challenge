package com.br.wirecard.paymentapichallenge.resource;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.br.wirecard.paymentapichallenge.model.Payment.PaymentType;

@Converter
public class PaymentTypeJpaConverter implements AttributeConverter<PaymentType, String> {

	@Override
	public String convertToDatabaseColumn(PaymentType paymentType) {
		if (paymentType == null) {
			return null;
		}
		return paymentType.toString();
	}

	@Override
	public PaymentType convertToEntityAttribute(String string) {
		if (string == null) {
			return null;
		}
		try {
			return PaymentType.valueOf(string);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}

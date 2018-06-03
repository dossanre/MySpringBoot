package com.dossanre.coursemc.domain;

import javax.persistence.Entity;

import com.dossanre.coursemc.domain.enums.PaymentState;
@Entity
public class CardPayment extends Payment{

	private static final long serialVersionUID = 1L;
	
	private Integer installments;
	
	public CardPayment() {}

	public CardPayment(Integer id, PaymentState paymentState, Order order,Integer installments) {
		super(id, paymentState, order);
		this.installments = installments;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}
	
	
	
}

package com.dossanre.coursemc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.dossanre.coursemc.domain.enums.PaymentState;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class BilledPayment extends Payment{

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="MM/dd/yyyy HH:mm")
	private Date expireDate; 
	@JsonFormat(pattern="MM/dd/yyyy HH:mm")
	private Date paymentDate;
	
	public BilledPayment() {}

	public BilledPayment(Integer id, PaymentState paymentState, Order order, Date expireDate, Date paymentDate ) {
		super(id, paymentState, order);
		this.expireDate = expireDate;
		this.paymentDate = paymentDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
	
}

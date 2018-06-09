package com.dossanre.coursemc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.dossanre.coursemc.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PaymentInvoice extends Payment{

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="MM/dd/yyyy HH:mm")
	private Date expireDate; 
	@JsonFormat(pattern="MM/dd/yyyy HH:mm")
	private Date paymentDate;
	
	public PaymentInvoice() {}

	public PaymentInvoice(Integer id, PaymentStatus paymentState, Order order, Date expireDate, Date paymentDate ) {
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

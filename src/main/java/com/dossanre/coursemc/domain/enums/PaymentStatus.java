package com.dossanre.coursemc.domain.enums;

public enum PaymentStatus {

	PENDING(1,"Pending"),
	PAYED(2, "Payed"),
	CANCELED(3,"Canceled");
	
	private int cod; 
	private String description; 
	
	private PaymentStatus(int cod, String description) {
		this.cod=cod;
		this.description = description;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static PaymentStatus toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		for (PaymentStatus payment : PaymentStatus.values()) {
			if(cod.equals(payment.cod)) {
				return payment;
			}
		}
		
		throw new IllegalArgumentException("Invalid Payment Code "+cod);
		
	}
}

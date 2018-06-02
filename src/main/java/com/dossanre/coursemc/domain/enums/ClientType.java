package com.dossanre.coursemc.domain.enums;

public enum ClientType {
	
	INDIVIDUAL(1,"Individual"),
	LEGALENTITY(2,"Legal Entity");
	
	private int cod; 
	private String description; 
	
	private ClientType(int cod, String description) {
		this.cod=cod;
		this.description = description;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescription() {
		return description;
	}
	
	public static ClientType toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		for (ClientType client : ClientType.values()) {
			if(cod.equals(client.cod)) {
				return client;
			}
		}
		
		throw new IllegalArgumentException("Invalid Code "+cod);
		
	}

}

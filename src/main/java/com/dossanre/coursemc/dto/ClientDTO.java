package com.dossanre.coursemc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.dossanre.coursemc.domain.Client;

public class ClientDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer id; 
	@NotEmpty
	@Length(min=5, max=120, message="The lenght must be between 5 and 120 Characters")
	private String name; 
	@NotEmpty
	@Email(message="Invalid Email")
	private String email; 
	
	public ClientDTO() {}

	public ClientDTO(Client client) {
		super();
		this.id = client.getId();
		this.name = client.getName();
		this.email = client.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}

package com.dossanre.coursemc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.dossanre.coursemc.domain.Category;

public class CategoryDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Field Requested")
	@Length(min=5, max=80, message="The lenght must be between 5 and 80 Characters")
	private String name; 
	
	public CategoryDTO() {}

	public CategoryDTO(Category categ) {
		this.id = categ.getId();
		this.name = categ.getName();
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
	
	
	
}

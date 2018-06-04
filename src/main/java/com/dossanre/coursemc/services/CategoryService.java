package com.dossanre.coursemc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.dossanre.coursemc.domain.Category;
import com.dossanre.coursemc.repositories.CategoryRepository;
import com.dossanre.coursemc.services.exceptions.DataIntegrityException;
import com.dossanre.coursemc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repo;
	
	public Category find(Integer id) {
		
		Optional<Category> obj = repo.findById(id);  
		return obj.orElseThrow(() -> new ObjectNotFoundException(    
				"Category Not Found! Id: " + id + ", Type: " + Category.class.getName())); 
		
	}
	
	public Category insert(Category categ) {
		categ.setId(null);
		return repo.save(categ);
	}
	
	public Category update(Category categ) {
		find(categ.getId());
		return repo.save(categ);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Can not delete categories with products.");
		}
	}
	
	public List<Category> findAll() {
		return repo.findAll();
	}

}

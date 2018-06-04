package com.dossanre.coursemc.resources;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dossanre.coursemc.domain.Category;
import com.dossanre.coursemc.services.CategoryService;

@RestController
@RequestMapping(value="/category")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Category> find(@PathVariable Integer id){
		
		Category obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	// Insert Category into Database
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Category categ){
		categ = service.insert(categ);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(categ.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Category categ, @PathVariable Integer id){
		categ.setId(id);
		categ = service.update(categ);
		return ResponseEntity.noContent().build();
	}

}

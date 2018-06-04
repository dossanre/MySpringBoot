package com.dossanre.coursemc.resources;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dossanre.coursemc.domain.Category;
import com.dossanre.coursemc.dto.CategoryDTO;
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
	// Update Category
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Category categ, @PathVariable Integer id){
		categ.setId(id);
		categ = service.update(categ);
		return ResponseEntity.noContent().build();
	}
	// Delete Category
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> findAll(){
		List<Category> listCateg = service.findAll();
		List<CategoryDTO> listDto = listCateg.stream().map(obj-> new CategoryDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	// Using GET 
	//http://localhost:8080/category/page?linesPerPage=3
	//http://localhost:8080/category/page?linesPerPage=3&page=1&direction=DESC
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<CategoryDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue ="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue ="name")String orderBy,
			@RequestParam(value="direction", defaultValue ="ASC")String direction){
		Page<Category> listCateg = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoryDTO> listDto = listCateg.map(obj-> new CategoryDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	
	
}

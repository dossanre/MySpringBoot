package com.dossanre.coursemc.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dossanre.coursemc.domain.Order;
import com.dossanre.coursemc.services.OrderService;

@RestController
@RequestMapping(value="/order")
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id){
		
		Order obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}

}
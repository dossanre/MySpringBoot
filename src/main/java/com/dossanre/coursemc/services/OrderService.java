package com.dossanre.coursemc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dossanre.coursemc.domain.Order;
import com.dossanre.coursemc.repositories.OrderRepository;
import com.dossanre.coursemc.services.exceptions.ObjectNotFoundException;


@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repo;
	
	public Order find(Integer id) {
		
		Optional<Order> obj = repo.findById(id);  
		return obj.orElseThrow(() -> new ObjectNotFoundException(    
				"Order Not Found! Id: " + id + ", Type: " + Order.class.getName())); 
		
	}

}

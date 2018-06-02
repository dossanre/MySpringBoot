package com.dossanre.coursemc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dossanre.coursemc.domain.Client;
import com.dossanre.coursemc.repositories.ClientRepository;
import com.dossanre.coursemc.services.exceptions.ObjectNotFoundException;


@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repo;
	
	public Client find(Integer id) {
		
		Optional<Client> obj = repo.findById(id);  
		return obj.orElseThrow(() -> new ObjectNotFoundException(    
				"Client Not Found! Id: " + id + ", Type: " + Client.class.getName())); 
		
	}

}

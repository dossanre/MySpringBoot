package com.dossanre.coursemc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dossanre.coursemc.domain.Client;
import com.dossanre.coursemc.dto.ClientDTO;
import com.dossanre.coursemc.repositories.ClientRepository;
import com.dossanre.coursemc.services.exceptions.DataIntegrityException;
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
	
	public Client update(Client client) {
		Client newClient = find(client.getId());
		updateData(newClient, client);
		return repo.save(newClient);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Can not delete a client with orders.");
		}
	}
	
	public List<Client> findAll() {
		return repo.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linePerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO clientDTO ) {
		
		return new Client(clientDTO.getId(), clientDTO.getName(),clientDTO.getEmail(), null, null);
		
	}
	
	private void updateData(Client newClient, Client client) {
		newClient.setName(client.getName());
		newClient.setEmail(client.getName());
	}

}

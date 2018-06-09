package com.dossanre.coursemc.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dossanre.coursemc.domain.Client;
import com.dossanre.coursemc.dto.ClientDTO;
import com.dossanre.coursemc.services.ClientService;

@RestController
@RequestMapping(value="/client")
public class ClientResource {
	
	@Autowired
	private ClientService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Client> find(@PathVariable Integer id){
		
		Client obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	// Update Client
		@RequestMapping(value="/{id}",method=RequestMethod.PUT)
		public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO categDto, @PathVariable Integer id){
			Client categ = service.fromDTO(categDto);
			categ.setId(id);
			categ = service.update(categ);
			return ResponseEntity.noContent().build();
		}
		// Delete Client
		@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
		public ResponseEntity<Void> delete(@PathVariable Integer id){
			service.delete(id);
			return ResponseEntity.noContent().build();
		}

		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<ClientDTO>> findAll(){
			List<Client> listCateg = service.findAll();
			List<ClientDTO> listDto = listCateg.stream().map(obj-> new ClientDTO(obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listDto);
		}
		// Using GET 
		//http://localhost:8080/category/page?linesPerPage=3
		//http://localhost:8080/category/page?page=1&linesPerPage=3&direction=DESC
		@RequestMapping(value="/page",method=RequestMethod.GET)
		public ResponseEntity<Page<ClientDTO>> findPage(
				@RequestParam(value="page", defaultValue="0") Integer page,
				@RequestParam(value="linesPerPage", defaultValue ="24") Integer linesPerPage,
				@RequestParam(value="orderBy", defaultValue ="name")String orderBy,
				@RequestParam(value="direction", defaultValue ="ASC")String direction){
			Page<Client> listCateg = service.findPage(page, linesPerPage, orderBy, direction);
			Page<ClientDTO> listDto = listCateg.map(obj-> new ClientDTO(obj));
			return ResponseEntity.ok().body(listDto);
		}
		
	

}

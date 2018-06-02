package com.dossanre.coursemc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dossanre.coursemc.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	

}
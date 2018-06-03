package com.dossanre.coursemc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dossanre.coursemc.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	

}

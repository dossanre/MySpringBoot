package com.dossanre.coursemc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dossanre.coursemc.domain.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer>{
	
	

}

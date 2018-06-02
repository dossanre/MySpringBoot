package com.dossanre.coursemc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dossanre.coursemc.domain.Category;
import com.dossanre.coursemc.domain.City;
import com.dossanre.coursemc.domain.Product;
import com.dossanre.coursemc.domain.Province;
import com.dossanre.coursemc.repositories.CategoryRepository;
import com.dossanre.coursemc.repositories.CityRepository;
import com.dossanre.coursemc.repositories.ProductRepository;
import com.dossanre.coursemc.repositories.ProvinceRepository;

@SpringBootApplication
public class CoursemcApplication implements CommandLineRunner{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProvinceRepository provinceRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CoursemcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Create objects of class Category and Product and fill them into the H2 database
		Category cat1 = new Category(null, "Computer");
		Category cat2 = new Category(null, "Office");
		
		Product p1 = new Product(null, "Laptop", 2000.00);
		Product p2 = new Product(null, "Printer", 1000.00);
		Product p3 = new Product(null, "Keyboard", 1500.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1,cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		// Save objects into database H2
		categoryRepository.saveAll(Arrays.asList(cat1,cat2));
		productRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		// Create objects of class Province and Cities and fill them into the H2 database
		Province prov1 = new Province(null, "Ontario"); 
		Province prov2 = new Province(null, "Quebec"); 
		
		City city1 = new City(null, "Toronto",prov1);
		City city2 = new City(null, "Montreal",prov2);
		City city3 = new City(null, "Quebec City",prov2);
		
		prov1.getCities().addAll(Arrays.asList(city1));
		prov2.getCities().addAll(Arrays.asList(city2,city3));
		
		// Save objects into database H2
		provinceRepository.saveAll(Arrays.asList(prov1,prov2));
		cityRepository.saveAll(Arrays.asList(city1,city2,city3));

	}
	

}

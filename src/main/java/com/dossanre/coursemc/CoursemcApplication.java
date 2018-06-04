package com.dossanre.coursemc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dossanre.coursemc.domain.Address;
import com.dossanre.coursemc.domain.BilledPayment;
import com.dossanre.coursemc.domain.CardPayment;
import com.dossanre.coursemc.domain.Category;
import com.dossanre.coursemc.domain.City;
import com.dossanre.coursemc.domain.Client;
import com.dossanre.coursemc.domain.Order;
import com.dossanre.coursemc.domain.OrderItem;
import com.dossanre.coursemc.domain.Payment;
import com.dossanre.coursemc.domain.Product;
import com.dossanre.coursemc.domain.Province;
import com.dossanre.coursemc.domain.enums.ClientType;
import com.dossanre.coursemc.domain.enums.PaymentState;
import com.dossanre.coursemc.repositories.AddressRepository;
import com.dossanre.coursemc.repositories.CategoryRepository;
import com.dossanre.coursemc.repositories.CityRepository;
import com.dossanre.coursemc.repositories.ClientRepository;
import com.dossanre.coursemc.repositories.OrderItemRepository;
import com.dossanre.coursemc.repositories.OrderRepository;
import com.dossanre.coursemc.repositories.PaymentRepository;
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
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CoursemcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// Create objects of class Category and Product and fill them into the H2 database
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Office");
		Category cat3 = new Category(null, "Home Accessories");
		Category cat4 = new Category(null, "Furniture");
		Category cat5 = new Category(null, "Sport");
		Category cat6 = new Category(null, "Baby");
		Category cat7 = new Category(null, "Health, Beauty & Pharmacy");
		
		Product product1 = new Product(null, "Laptop", 2000.00);
		Product product2 = new Product(null, "Printer", 1000.00);
		Product product3 = new Product(null, "Keyboard", 1500.00);
		
		cat1.getProducts().addAll(Arrays.asList(product1, product2, product3));
		cat2.getProducts().addAll(Arrays.asList(product2));
		
		product1.getCategories().addAll(Arrays.asList(cat1));
		product2.getCategories().addAll(Arrays.asList(cat1,cat2));
		product3.getCategories().addAll(Arrays.asList(cat1));
		
		// Save objects into database H2
		categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		productRepository.saveAll(Arrays.asList(product1,product2,product3));
		
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
		
		Client cli1 = new Client(null, "John Smith", "john@gmail.com","123456789",ClientType.INDIVIDUAL);
		cli1.getPhones().addAll(Arrays.asList("6477011919","6477011920"));
		
		Address address1 = new Address(null,"215 Main Street","L6Y 1M6",city1,cli1);
		Address address2 = new Address(null,"405 Engliton","M1T 7M8",city1,cli1);
		
		cli1.getAddresses().addAll(Arrays.asList(address1,address2));
		
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(address1,address2));
		
		// New Order and Payment
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		Order order1 = new Order(null, sdf.parse("06/02/2018 18:42"),cli1, address1 );
		Order order2 = new Order(null, sdf.parse("06/02/2018 19:40"),cli1, address2 );
		
		Payment payment1 = new CardPayment(null, PaymentState.PAYED, order1, 6);
		order1.setPayment(payment1);
		
		Payment payment2 = new BilledPayment(null, PaymentState.PENDING, order2, sdf.parse("06/02/2018 20:00"), null);
		order2.setPayment(payment2);
		
		cli1.getOrders().addAll(Arrays.asList(order1,order2));
		
		orderRepository.saveAll(Arrays.asList(order1, order2));
		paymentRepository.saveAll(Arrays.asList(payment1, payment2));
	
		// New Order Item 
		OrderItem orderItem1 = new OrderItem(order1,product1, 0.00, 1, 2000.00);
		OrderItem orderItem2 = new OrderItem(order1,product3, 0.00, 2, 80.00);
		OrderItem orderItem3 = new OrderItem(order2,product2, 100.00, 1, 800.00);
		
		order1.getItems().addAll(Arrays.asList(orderItem1, orderItem2));
		order2.getItems().addAll(Arrays.asList(orderItem3));
		
		product1.getItems().addAll(Arrays.asList(orderItem1));
		product2.getItems().addAll(Arrays.asList(orderItem3));
		product3.getItems().addAll(Arrays.asList(orderItem2));
		
		orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2,orderItem3));
		
	}
	
}

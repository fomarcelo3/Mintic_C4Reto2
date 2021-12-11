package com.mintic.tienda;

import com.mintic.tienda.repository.crud.ClotheCrudRepository;
import com.mintic.tienda.repository.crud.OrderCrudRepository;
import com.mintic.tienda.repository.crud.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class TiendaApplication implements CommandLineRunner {

	@Autowired
	private ClotheCrudRepository clotheCrudRepository;
	@Autowired
	private UserCrudRepository userCrudRepository;
	@Autowired
	private OrderCrudRepository orderCrudRepository;

	public static void main(String[] args) {
		SpringApplication.run(TiendaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		clotheCrudRepository.deleteAll();
		userCrudRepository.deleteAll();
		orderCrudRepository.deleteAll();
	}

}

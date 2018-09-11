package com.example.customerapi;

import com.example.customerapi.domain.Customer;
import com.example.customerapi.repository.CustomerRepository;
import com.example.customerapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class CustomerApiApplication {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
    CustomerService customerService;

	public static void main(String args[]){
        SpringApplication.run(CustomerApiApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(){
		return args -> {
			customerRepository.deleteAll()
					.subscribe(null, null,
							() -> getCustomers()
                                    .forEach( customer -> customerRepository.save(customer)
                                            .subscribe(System.out::println)));

		};
	}


	private Stream<Customer> getCustomers(){
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(UUID.randomUUID().toString(), "Joh","Doe", "Black", "11111111111"));
		customers.add(new Customer(UUID.randomUUID().toString(), "Carla","Oed", "Silver", "222222"));
		customers.add(new Customer(UUID.randomUUID().toString(), "Daniela","EOi", "Silver", "333333"));
		customers.add(new Customer(UUID.randomUUID().toString(), "Fernando","Eqat", "GOLD", "77777777777777"));
		customers.add(new Customer(UUID.randomUUID().toString(), "Yerko","Rakitich", "BLACK", "33333333333"));
		customers.add(new Customer(UUID.randomUUID().toString(), "Maria","La del Barrio", "Platinium", "5555555555555556666666666"));
		return customers.stream();
	}
}

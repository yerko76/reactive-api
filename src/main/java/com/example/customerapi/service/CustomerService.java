package com.example.customerapi.service;

import com.example.customerapi.domain.CustomerEvent;
import com.example.customerapi.repository.CustomerRepository;
import com.example.customerapi.domain.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Flux<Customer> all(){
        return customerRepository.findAll();
    }

    public Mono<Customer> byId(String id){
        return customerRepository.findById(id);
    }

    public Flux<CustomerEvent> byCustomer(Customer customer){
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        Flux<CustomerEvent> events = Flux.fromStream(Stream.generate(()-> new CustomerEvent(customer, new Date(), getEvent())));

        return Flux.zip(interval, events).map(Tuple2::getT2);

    }

    private String getEvent(){
        String[] events = "Requesting Trip,Updating Email,Upgrading services,Redeem miles".split(",");
        return events[new Random().nextInt(events.length)];
    }
}

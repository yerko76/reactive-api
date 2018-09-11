package com.example.customerapi.rest;

import com.example.customerapi.domain.Customer;
import com.example.customerapi.domain.CustomerEvent;
import com.example.customerapi.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/customer")
public class CustomerResource {

    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public Flux<Customer> getAll(){
        return customerService.all();
    }

    @GetMapping("/{id}")
    public Mono<Customer> byId(@PathVariable String id){
        return customerService.byId(id);
    }

    @GetMapping(value="/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CustomerEvent> events(@PathVariable String id) {
        return customerService.byId(id)
                .flatMapMany(customerService::byCustomer);
    }
}

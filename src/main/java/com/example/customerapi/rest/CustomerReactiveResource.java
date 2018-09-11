//package com.example.customerapi.rest;
//
//import com.example.customerapi.domain.Customer;
//import com.example.customerapi.domain.CustomerEvent;
//import com.example.customerapi.service.CustomerService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.RouterFunctions;
//import org.springframework.web.reactive.function.server.ServerResponse;
//
//import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
//
//@Configuration
//public class CustomerReactiveResource {
//    private final CustomerService customerService;
//
//    public CustomerReactiveResource(CustomerService customerService) {
//        this.customerService = customerService;
//    }
//
//
//    @Bean
//    public RouterFunction<?> route(){
//        return RouterFunctions
//                .route(GET("/customer/all"),
//                        serverRequest -> ServerResponse.ok().body(customerService.all(), Customer.class))
//                .andRoute(GET("/customer/{id}"),
//                        serverRequest -> ServerResponse.ok().body(customerService.byId(serverRequest.pathVariable("id")), Customer.class))
//                .andRoute(GET("/customer/{id}/events"),
//                        serverRequest -> ServerResponse.ok()
//                                .contentType(MediaType.TEXT_EVENT_STREAM)
//                                .body(customerService.byId(serverRequest.pathVariable("id"))
//                                        .flatMap(customer -> customerService.byCustomer(customer), CustomerEvent.class));
//    }
//
//
//}
//

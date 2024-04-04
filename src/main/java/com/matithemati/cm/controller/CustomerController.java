package com.matithemati.cm.controller;

import com.matithemati.cm.model.Customer;
import com.matithemati.cm.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }
}

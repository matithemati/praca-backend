package com.matithemati.cm.service;

import com.matithemati.cm.model.Customer;
import com.matithemati.cm.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(String id) {
        return customerRepository.findById(Long.parseLong(id)).orElse(null);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}

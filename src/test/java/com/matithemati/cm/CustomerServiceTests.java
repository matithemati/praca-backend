package com.matithemati.cm;

import com.matithemati.cm.model.Customer;
import com.matithemati.cm.repository.CustomerRepository;
import com.matithemati.cm.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void testGetAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setEmail("example@test.com");
        customer1.setAddress("test");
        customer1.setPhone(123123123);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setEmail("test@example.com");
        customer2.setAddress("test");
        customer2.setPhone(123123123);

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        List<Customer> customers = customerService.getAllCustomers();

        assertEquals(2, customers.size());
        assertEquals("John", customers.get(0).getFirstName());
        assertEquals("Jane", customers.get(1).getFirstName());
    }

    @Test
    void testGetCustomerById() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("example@test.com");
        customer.setAddress("test");
        customer.setPhone(123123123);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // Act
        Customer retrievedCustomer = customerService.getCustomerById("1");

        // Assert
        assertEquals("John", retrievedCustomer.getFirstName());
    }

    @Test
    void testAddCustomer() {
        // Arrange
        Customer newCustomer = new Customer();
        newCustomer.setFirstName("Alice");
        newCustomer.setLastName("Doe");
        newCustomer.setEmail("alice@test.com");
        newCustomer.setAddress("test");
        newCustomer.setPhone(123123123);

        when(customerRepository.save(newCustomer)).thenReturn(newCustomer);

        // Act
        Customer addedCustomer = customerService.addCustomer(newCustomer);

        // Assert
        assertEquals("Alice", addedCustomer.getFirstName());
        verify(customerRepository, times(1)).save(newCustomer);
    }

    @Test
    void testGetCustomerByEmail() {
        // Arrange
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john@example.com");
        customer.setAddress("test");
        customer.setPhone(123123123);

        when(customerRepository.findByEmail("john@example.com")).thenReturn(customer);

        // Act
        Customer retrievedCustomer = customerService.getCustomerByEmail("john@example.com");

        // Assert
        assertEquals("John", retrievedCustomer.getFirstName());
    }
}


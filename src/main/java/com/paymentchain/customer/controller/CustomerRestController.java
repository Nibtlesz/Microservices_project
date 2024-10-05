package com.paymentchain.customer.controller;

import com.paymentchain.customer.entities.Customer;
import com.paymentchain.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/All")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            Customer customerToUpdate = customerOptional.get();
            customerToUpdate.setName(customer.getName());
            customerToUpdate.setPhone(customer.getPhone());

            Customer save = customerRepository.save(customerToUpdate);

            return ResponseEntity.ok(save);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
        Customer save = customerRepository.save(customer);
        return ResponseEntity.ok(save);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        if (id != null) {
            customerRepository.deleteById(id);
        }
        return ResponseEntity.ok().build();
    }

}

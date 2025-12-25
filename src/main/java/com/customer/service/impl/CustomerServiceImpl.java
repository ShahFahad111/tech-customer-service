package com.customer.service.impl;

import com.customer.entity.Customer;
import com.customer.exception.CustomerAlreadyExistsException;
import com.customer.exception.CustomerNotFoundException;
import com.customer.repository.CustomerRepository;
import com.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()){
            throw new CustomerNotFoundException(id);
        }
        return customer;
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.getCustomerByEmail(email);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerByEmail = getCustomerByEmail(customer.getEmail());
        if(customerByEmail != null){
            throw new CustomerAlreadyExistsException(customer.getEmail());
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer, Long id) {
        Optional<Customer> custOps = customerRepository.findById(id);
        Customer updatedCustomer = null;
        if (custOps.isPresent()) {
            updatedCustomer =custOps.get();
            updatedCustomer.setName(customer.getName());
            updatedCustomer.setEmail(customer.getEmail());
            updatedCustomer.setLocation( customer.getLocation());
        }
        return customerRepository.save(updatedCustomer);
    }

    @Override
    public String deleteCustomer(Long id) {
        Optional<Customer> cust = customerRepository.findById(id);
        String deleteMsg = null;
        Customer c = null;
        if (cust.isPresent()) {
            c = cust.get();
            customerRepository.delete(c);
            deleteMsg = "Customer with id " + id + " has been deleted";
        }
        return deleteMsg;
    }
}

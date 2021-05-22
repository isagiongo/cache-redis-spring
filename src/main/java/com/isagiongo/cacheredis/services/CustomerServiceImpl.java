package com.isagiongo.cacheredis.services;

import com.isagiongo.cacheredis.exceptions.CustomerNotFoundException;
import com.isagiongo.cacheredis.models.Customer;
import com.isagiongo.cacheredis.repositories.CustomerRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "customerCache")
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Cacheable(cacheNames = "customers")
    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @CacheEvict(cacheNames = "customers", allEntries = true)
    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @CacheEvict(cacheNames = "customers", allEntries = true)
    @Override
    public Customer update(Customer customer) {
        Customer repositoryCustomer = findById(customer.getId());
        repositoryCustomer.setName(customer.getName());
        repositoryCustomer.setCpf(customer.getCpf());
        repositoryCustomer.setAddress(customer.getAddress());
        repositoryCustomer.setCity(customer.getCity());
        repositoryCustomer.setPostalCode(customer.getPostalCode());
        repositoryCustomer.setCountry(customer.getCountry());
        return customerRepository.save(repositoryCustomer);
    }

    @Caching(evict = { @CacheEvict(cacheNames = "customer", key = "#id"),
            @CacheEvict(cacheNames = "customers", allEntries = true) })
    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Cacheable(cacheNames = "customer", key = "#id", unless = "#result == null")
    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }
}

package com.isagiongo.cacheredis.services;

import com.isagiongo.cacheredis.models.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> getAll();
    public Customer save(Customer customer);
    public Customer update(Customer customer);
    public void delete(Long id);
    public Customer findById(Long id);
}

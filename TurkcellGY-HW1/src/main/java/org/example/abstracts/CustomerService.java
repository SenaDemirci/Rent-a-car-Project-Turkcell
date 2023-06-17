package org.example.abstracts;

import org.example.entities.Customer;

import java.util.ArrayList;

public interface CustomerService {
    void register(Customer customer, ArrayList<Customer> customers);

    void update(Customer customer, ArrayList<Customer> customers);

    void delete(Customer customer, ArrayList<Customer> customers);
}

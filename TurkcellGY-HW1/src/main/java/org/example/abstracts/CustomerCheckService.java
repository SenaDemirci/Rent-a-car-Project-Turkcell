package org.example.abstracts;

import org.example.entities.Customer;

import java.util.ArrayList;

public interface CustomerCheckService {
    boolean check(Customer customer, ArrayList<Customer> customers);
}

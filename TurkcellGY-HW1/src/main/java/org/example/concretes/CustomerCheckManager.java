package org.example.concretes;

import org.example.abstracts.CustomerCheckService;
import org.example.entities.Customer;

import java.util.ArrayList;
import java.util.Objects;

public class CustomerCheckManager implements CustomerCheckService {
    @Override
    public boolean check(Customer customer, ArrayList<Customer> customers) {
        for (Customer customer1 : customers) {
            if (Objects.equals(customer1.getNationalIdentity(), customer.getNationalIdentity()) && Objects.equals(customer1.getFirstName(), customer.getFirstName())
                    && Objects.equals(customer1.getLastName(), customer.getLastName()) && customer1.getYearOfBirth() == customer.getYearOfBirth()) {
                return true;
            }
        }
        return false;
    }

}


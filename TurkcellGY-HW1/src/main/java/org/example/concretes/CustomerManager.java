package org.example.concretes;

import org.example.abstracts.CustomerCheckService;
import org.example.abstracts.CustomerService;
import org.example.entities.Customer;

import java.util.ArrayList;

public class CustomerManager implements CustomerService {
    private CustomerCheckService customerCheckService;


    public CustomerManager(CustomerCheckService customerCheckService) {
        this.customerCheckService = customerCheckService;
    }

    @Override
    public void register(Customer customer, ArrayList<Customer> customers) {

        if (customerCheckService.check(customer, customers)) {
            System.out.println("Customer verified: " + customer.getFirstName());
            System.out.println("Customer registered: " + customer.getFirstName());
        } else {
            System.out.println("Customer not verified: " + customer.getFirstName());
            System.out.println("Customer not registered: " + customer.getFirstName());
        }
    }

    @Override
    public void update(Customer customer, ArrayList<Customer> customers) {
        if (customerCheckService.check(customer, customers)) {
            System.out.println("Customer updated: " + customer.getFirstName());
        } else {
            System.out.println("No customer found for update operation: " + customer.getFirstName() + "! Make sure that you registered before!");
        }
    }

    @Override
    public void delete(Customer customer, ArrayList<Customer> customers) {
        if (customerCheckService.check(customer, customers)) {
            System.out.println("Customer deleted: " + customer.getFirstName());
        } else {
            System.out.println("No customer found for delete operation: " + customer.getFirstName() + "! Make sure that you registered before!");
        }
    }

}

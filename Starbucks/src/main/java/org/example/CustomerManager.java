package org.example;

public class CustomerManager {
    //kendi service injection
    private EdevletCheckService service;

    public CustomerManager(EdevletCheckService service) {
        this.service = service;
    }

    public void register(Customer customer){
        if(service.checkCustomerAvaible(customer)){
            System.out.println("Register successful");
        }
        else {
            System.out.println("Register failure");
        }
    }



}

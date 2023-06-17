package org.example;

//benim e devletim
public class EdevletCheckServiceAdapter implements EdevletCheckService{

    private EDevletService service;

    public EdevletCheckServiceAdapter(EDevletService service) {
        this.service = service;
    }

    @Override
    public boolean checkCustomerAvaible(Customer customer) {
        if(service.checkCustomer(customer)){
            return true;
        }
        return false;
    }
}

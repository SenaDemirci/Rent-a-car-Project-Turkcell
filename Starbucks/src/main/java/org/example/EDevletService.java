package org.example;

import java.util.ArrayList;
import java.util.List;

// Edevlet sistemi
public class EDevletService {
    private List<Customer> customers=new ArrayList<>();

    public EDevletService() {
        customers.add(new Customer("Esra","Aydın","31375245184",1995));
        customers.add(new Customer("Aslı","Koçak","12369854712",1995));
    }

    public boolean checkCustomer(Customer customer){
        for (Customer cstmr : customers) {
            if(customer.equals(cstmr)){
                return true;
            }
        }
        return false;
    }



}

package org.example;

public class CustomerManager {
    //CRUD operations
    public void add(EmailLogger emailLogger) {
        //iş yapacak sınıfta newleme yapılmamalı.
        System.out.println("Müşteri eklendi");
        DatabaseLogger logger = new DatabaseLogger();
        logger.log();
    }
}

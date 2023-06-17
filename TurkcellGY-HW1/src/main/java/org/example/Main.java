package org.example;

import org.example.concretes.CampaignManager;
import org.example.concretes.CustomerCheckManager;
import org.example.concretes.CustomerManager;
import org.example.concretes.GameOrderManager;
import org.example.entities.Campaign;
import org.example.entities.Customer;
import org.example.entities.Game;

import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        customers.add(new Customer(1, "John", "Doe", "12345678910", 1999));
        customers.add(new Customer(2, "Jane", "Doe", "12345678911", 1998));
        customers.add(new Customer(3, "Jack", "Doe", "12345678912", 1997));
        customers.add(new Customer(4, "Jill", "Doe", "12345678913", 1996));
        customers.add(new Customer(5, "Joe", "Doe", "12345678914", 1995));


        Customer customer1 = new Customer(1, "John", "Doe", "12345678910", 1999);
        Customer customer2 = new Customer(2, "Jane", "Doe", "12345678911", 1998);
        Customer customer3 = new Customer(3, "Jack", "Doe", "12345678912", 1996);
        Game game1 = new Game(1, "GTA V", 200, "Action", new Date());
        Campaign campaign1 = new Campaign(1, "Summer Sale", "Summer Sale", 50, new Date(), new Date(2023, 11, 10, 22, 10));

        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(customer1);
        System.out.println(customer2);
        System.out.println(customer3);
        System.out.println(game1);
        System.out.println(campaign1);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");

        CustomerManager customerManager = new CustomerManager(new CustomerCheckManager());
        customerManager.register(customer2, customers);
        customerManager.register(customer1, customers);
        customerManager.update(customer1, customers);
        customerManager.delete(customer1, customers);
        customerManager.register(customer3, customers);
        customerManager.update(customer3, customers);
        customerManager.delete(customer3, customers);


        CampaignManager campaignManager = new CampaignManager();
        campaignManager.add(campaign1);
        campaignManager.update(campaign1);
        campaignManager.delete(campaign1);

        GameOrderManager gameOrderManagerManager = new GameOrderManager(new CustomerCheckManager());
        gameOrderManagerManager.order(customer1, game1, customers);
        gameOrderManagerManager.orderWithCampaign(customer1, game1, campaign1, customers);
        gameOrderManagerManager.order(customer3, game1, customers);

        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");

    }
}
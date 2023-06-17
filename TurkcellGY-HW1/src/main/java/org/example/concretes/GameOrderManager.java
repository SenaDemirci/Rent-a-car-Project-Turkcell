package org.example.concretes;

import org.example.abstracts.CustomerCheckService;
import org.example.abstracts.GameOrderService;
import org.example.entities.Campaign;
import org.example.entities.Customer;
import org.example.entities.Game;

import java.util.ArrayList;

public class GameOrderManager implements GameOrderService {

    private CustomerCheckService customerCheckService;


    public GameOrderManager(CustomerCheckService customerCheckService) {
        this.customerCheckService = customerCheckService;
    }

    @Override
    public void order(Customer customer, Game game, ArrayList<Customer> customers) {

        if (customerCheckService.check(customer, customers)) {
            System.out.println("Order created for: " + game.getName() + " Enjoy it " + customer.getFirstName() + "! ");
        } else {
            System.out.println("Customer not registered: " + customer.getFirstName());
            System.out.println("Order cannot created for: " + game.getName() + ":" + customer.getFirstName() + "! ");
        }
    }

    @Override
    public void orderWithCampaign(Customer customer, Game game, Campaign campaign, ArrayList<Customer> customers) {
        double newPrice = game.getPrice() - (game.getPrice() * campaign.getDiscountRate() / 100);
        if (customerCheckService.check(customer, customers)) {
            System.out.println("Order created for: " + game.getName() + " Enjoy it " + customer.getFirstName() + "! " + campaign.getName() + " applied, the price was reduced from "
                    + game.getPrice() + " to " + newPrice + " !");
        } else {
            System.out.println("Customer not registered: " + customer.getFirstName());
            System.out.println("Order cannot created for: " + game.getName() + ":" + customer.getFirstName() + "! ");
        }
    }
}

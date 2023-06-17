package org.example.abstracts;

import org.example.entities.Campaign;
import org.example.entities.Customer;
import org.example.entities.Game;

import java.util.ArrayList;

public interface GameOrderService {
    void order(Customer customer, Game game, ArrayList<Customer> customers);

    void orderWithCampaign(Customer customer, Game game, Campaign campaign, ArrayList<Customer> customers);
}


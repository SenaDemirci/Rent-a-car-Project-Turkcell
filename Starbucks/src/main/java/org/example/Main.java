package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Customer customer=new Customer();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Starbucks Hoşgeldiniz !!!");
        System.out.print("Lütfen Adınızı girin:");
        customer.setFirstName(scanner.nextLine());
        System.out.print("Lütfen Soyadınızı girin:");
        customer.setLastName(scanner.nextLine());
        System.out.print("Lütfen Tc Numarısını girin:");
        customer.setIdentity(scanner.nextLine());
        System.out.print("Lütfen Doğum yılınızı girin:");
        customer.setBirthYear(scanner.nextInt());
        System.out.print("**********************");

        CustomerManager manager=new CustomerManager(new EdevletCheckServiceAdapter(new EDevletService()));
        manager.register(customer);


    }
}
package org.example;

public class Main {
    public static void main(String[] args) {
        CustomerManager manager = new CustomerManager(new Logger[]{new FileLogger(), new DatabaseLogger()});
        manager.add();
    }
}
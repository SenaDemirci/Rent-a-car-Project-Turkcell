package org.example;

//Loglama işlemi
public class Main {
    public static void main(String[] args) {
        //ConsoleUI (konsolu user interface) olarak kullanıcaz.
        LogManager logManager = new LogManager();
        logManager.log(1);
        CustomerManager manager = new CustomerManager();
        manager.add(new EmailLogger());

    }
}
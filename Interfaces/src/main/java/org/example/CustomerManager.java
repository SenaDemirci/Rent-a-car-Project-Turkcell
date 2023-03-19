package org.example;

public class CustomerManager {
    //Dependency injection
    private Logger[] logger;
    //loosely
    public CustomerManager(Logger[] logger) {
        this.logger = logger;
    }


    //loosely(esnek bağımlılık) / tightly(sıkı bağ.) coupled
    public void add() {
        System.out.println("Müşteri eklendi.");

        //tightly
        FileLogger fileLogger = new FileLogger();
        fileLogger.log("message");
    }
}

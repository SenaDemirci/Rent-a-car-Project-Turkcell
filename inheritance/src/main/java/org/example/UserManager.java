package org.example;

//db işlemlerinin yapıldığı repoya ve servis sınıflarına ulaşan iş sınıfı

//CRUD operations
public class UserManager {
    public void add(User user) {
        System.out.println(user.getFirstName() + " eklendi.");
    }
    public void addMultiple(User[] users) {
        for (User user : users) {
            add(user);
        }
    }
}

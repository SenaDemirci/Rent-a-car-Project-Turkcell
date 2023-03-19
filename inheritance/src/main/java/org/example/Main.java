package org.example;

//ctrl + shift + up satırı yukarı taşıma
public class Main {
    public static void main(String[] args) {
       UserManager userManager = new UserManager();

        Instructor instructor = new Instructor(1, "Engin", "Demiroğ", "kodlamaio");
        //userManager.add(user); //ctrl + alt + shift + T (refactor options)
        Applicant applicant = new Applicant(2, "Burak", "Kalaycı", "1111111111");

//        userManager.add(instructor);
//        userManager.add(applicant);
//        userManager.add(employee);

        // bulk insert (toplu veri ekleme işlemi)
        userManager.addMultiple(new User[] {instructor, applicant});
    }
}
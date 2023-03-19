package org.example;

//shift + F6 komple değiştir
//ctrl + / yorum satırı yap kapat
//ctrl + P parametrelere bakma

public class Main {
    public static void main(String[] args) {

        Product product1 = new Product(1, "Iphone 14", 30000, "telefon", 10);
        Product product2 = new Product(2, "Samsung", 25000, "tel", 10);
        Product product3 = new Product(3, "PS5", 20000, "cihaz", 15);

        Product[] products = {product1, product2, product3};

        for (Product product: products) {
            System.out.println(product.getId() + "/" + product.getName());
        }

        for (int i=0; i<3; i++) {
            System.out.println(products[i].getUnitPriceAfterDiscount());
        }


//        Bank bank1 = new Bank(2000);
//        int number = 300;
//        bank1.Decrease(number);
//        bank1.Increase(number);

    }
}
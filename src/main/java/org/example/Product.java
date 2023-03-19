package org.example;

//Javada classlar PascalCase, metotlar ve değişkenler camelCase olur
public class Product {
    private int id;
    private String name;
    private double unitPrice;
    private String description;
    private double discount;
    private double unitPriceAfterDiscount;

    public Product() { //no args constructor
        //this.id = id;
    }

    public Product(int id, String name, double unitPrice, String description, double discount) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.description = description;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getUnitPriceAfterDiscount() {
        return unitPrice - (unitPrice * discount / 100);
    }

    @Override
    public String toString() {
        //return "Product{}";
        return id + name;
    }
}

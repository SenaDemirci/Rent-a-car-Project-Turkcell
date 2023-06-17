package org.example.entities;

import java.util.Date;

public class Game extends BaseEntity {

    private String name;
    private double price;
    private String description;
    private Date releaseDate;

    public Game() {
    }

    public Game(int id, String name, double price, String description, Date releaseDate) {
        super(id);
        this.name = name;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }
}

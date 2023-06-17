package org.example.entities;

import java.util.Date;

public class Campaign extends BaseEntity {

    private String name;
    private String description;
    private double discountRate;
    private Date startDate;
    private Date endDate;

    public Campaign() {
    }

    public Campaign(int id, String name, String description, double discountRate, Date startDate, Date endDate) {
        super(id);
        this.name = name;
        this.description = description;
        this.discountRate = discountRate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", discountRate=" + discountRate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}



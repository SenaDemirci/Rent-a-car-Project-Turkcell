package org.example;

import java.util.Objects;

public class Customer {
    private String firstName;
    private String LastName;
    private String identity;
    private int BirthYear;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String identity, int birthYear) {
        this.firstName = firstName;
        LastName = lastName;
        this.identity = identity;
        BirthYear = birthYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getBirthYear() {
        return BirthYear;
    }

    public void setBirthYear(int birthYear) {
        BirthYear = birthYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return BirthYear == customer.BirthYear &&
                firstName.equals(customer.firstName) &&
                LastName.equals(customer.LastName) &&
                identity.equals(customer.identity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, LastName, identity, BirthYear);
    }
}

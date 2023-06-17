package org.example.entities;

public class Customer extends BaseEntity {

    private String firstName;
    private String lastName;
    private String nationalIdentity;
    private int yearOfBirth;

    public Customer() {
    }

    public Customer(int id, String firstName, String lastName, String nationalIdentity, int yearOfBirth) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalIdentity = nationalIdentity;
        this.yearOfBirth = yearOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNationalIdentity() {
        return nationalIdentity;
    }

    public void setNationalIdentity(String nationalIdentity) {
        this.nationalIdentity = nationalIdentity;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationalIdentity='" + nationalIdentity + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}

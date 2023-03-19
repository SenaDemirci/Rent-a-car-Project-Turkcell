package org.example;

public class Applicant extends User {
    private String tc;

    public Applicant(int id, String firstName, String lastName, String tc) {
        super(id, firstName, lastName); //base classın(User) constructorını çağırır.
        this.tc = tc;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }
}

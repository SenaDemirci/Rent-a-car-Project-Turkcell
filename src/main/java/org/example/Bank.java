package org.example;

public class Bank {
    private int account;
    public void Increase(int num) {
        if (num>0) {
            account = account+num;
            System.out.println(account);
        }
        else {
            System.out.println("cannot");
        }
    }
    public void Decrease(int num) {
        if (num > account || account<0) {
            System.out.println("bakiye yetersiz");
        }
        else {
            account = account - num;
            System.out.println(account);
        }

    }

    public Bank(int account) {
        this.account = account;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }
}

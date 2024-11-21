package com.example.demo.bo;

public class BalanceResponse {
    private Double balance;

    public BalanceResponse() {}

    public BalanceResponse(Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}

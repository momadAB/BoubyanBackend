package com.example.demo.bo;

public class OnboardingResponse {
    private String message;
    private Long customerId;
    private String accountNumber;
    private Double balance;

    public OnboardingResponse() {}

    public OnboardingResponse(String message, Long customerId, String accountNumber, Double balance) {
        this.message = message;
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}

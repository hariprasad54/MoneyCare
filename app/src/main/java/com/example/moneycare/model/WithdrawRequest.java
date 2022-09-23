package com.example.moneycare.model;

public class WithdrawRequest {
    private String userId;
    private String Date;
    private String amount;

    public WithdrawRequest(String userId, String date, String amount) {
        this.userId = userId;
        Date = date;
        this.amount = amount;
    }

    public WithdrawRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}

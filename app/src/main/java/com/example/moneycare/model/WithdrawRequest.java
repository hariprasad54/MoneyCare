package com.example.moneycare.model;

public class WithdrawRequest {
    private String userId;
    private String Date;
    private String amount;
    private String status;
    private String trnUpiId;

    public WithdrawRequest(String userId, String date, String amount) {
        this.userId = userId;
        Date = date;
        this.amount = amount;
    }

    public WithdrawRequest(String userId, String date, String amount, String status, String trnUpiId) {
        this.userId = userId;
        Date = date;
        this.amount = amount;
        this.status = status;
        this.trnUpiId = trnUpiId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrnUpiId() {
        return trnUpiId;
    }

    public void setTrnUpiId(String trnUpiId) {
        this.trnUpiId = trnUpiId;
    }
}

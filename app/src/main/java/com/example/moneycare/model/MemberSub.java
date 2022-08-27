package com.example.moneycare.model;

public class MemberSub {
    private String Email;
    private String phone;
    private String TransactionID;

    public MemberSub(String email, String phone, String transactionID) {
        Email = email;
        this.phone = phone;
        TransactionID = transactionID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(String transactionID) {
        TransactionID = transactionID;
    }
}

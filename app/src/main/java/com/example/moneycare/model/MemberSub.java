package com.example.moneycare.model;

public class MemberSub {

    private String firstName;
    private String lastName;
    private String Email;
    private String phone;
    private String TransactionID;

    public MemberSub(String email, String phone, String transactionID) {
        this.Email = email;
        this.phone = phone;
        this.TransactionID = transactionID;
    }

    public MemberSub(String firstName, String lastName, String email, String phone, String transactionID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.Email = email;
        this.phone = phone;
        this.TransactionID = transactionID;
    }


    public MemberSub() {
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
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
        this.TransactionID = transactionID;
    }
}

package com.example.moneycare.model;

public class BankAccount {
    private String bankName,ifscCode,acNumber;

    public BankAccount(String bankName, String ifscCode, String acNumber) {
        this.bankName = bankName;
        this.ifscCode = ifscCode;
        this.acNumber = acNumber;
    }

    public BankAccount() {
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAcNumber() {
        return acNumber;
    }

    public void setAcNumber(String acNumber) {
        this.acNumber = acNumber;
    }
}

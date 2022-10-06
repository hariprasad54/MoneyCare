package com.example.moneycare.model;

import com.example.moneycare.adapters.WithdrawRequestAdapter;

public class Transaction {
    public String trnId,trnEmail,trnDate,trnAmount,trnStatus,trnUpiId;

    public Transaction(String trnId, String trnEmail, String trnDate, String trnAmount) {
        this.trnId = trnId;
        this.trnEmail = trnEmail;
        this.trnDate = trnDate;
        this.trnAmount = trnAmount;
    }

    public Transaction(String trnEmail, String trnDate, String trnAmount) {
        this.trnEmail = trnEmail;
        this.trnDate = trnDate;
        this.trnAmount = trnAmount;
    }





    public Transaction() {
    }

    public Transaction(WithdrawRequest withdrawRequest){
        this.trnEmail = withdrawRequest.getUserId();
        this.trnDate = withdrawRequest.getDate();
        this.trnAmount = withdrawRequest.getAmount();
        this.trnStatus = withdrawRequest.getStatus();
        this.trnUpiId = withdrawRequest.getTrnUpiId();
    }

    public String getTrnId() {
        return trnId;
    }

    public void setTrnId(String trnId) {
        this.trnId = trnId;
    }

    public String getTrnEmail() {
        return trnEmail;
    }

    public void setTrnEmail(String trnEmail) {
        this.trnEmail = trnEmail;
    }

    public String getTrnDate() {
        return trnDate;
    }

    public void setTrnDate(String trnDate) {
        this.trnDate = trnDate;
    }

    public String getTrnAmount() {
        return trnAmount;
    }

    public void setTrnAmount(String trnAmount) {
        this.trnAmount = trnAmount;
    }

    public String getTrnStatus() {
        return trnStatus;
    }

    public void setTrnStatus(String trnStatus) {
        this.trnStatus = trnStatus;
    }

    public String getTrnUpiId() {
        return trnUpiId;
    }

    public void setTrnUpiId(String trnUpiId) {
        this.trnUpiId = trnUpiId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"trnId\": \"").append(trnId).append('\"');
        sb.append(", \"trnEmail\": \"").append(trnEmail).append('\"');
        sb.append(", \"trnDate\": \"").append(trnDate).append('\"');
        sb.append(", \"trnAmount\": \"").append(trnAmount).append('\"');
        sb.append(", \"trnStatus\": \"").append(trnStatus).append('\"');
        sb.append(", \"trnUpiId\": \"").append(trnUpiId).append('\"');
        sb.append("}");

        return sb.toString();
    }
}

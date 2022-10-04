package com.example.moneycare.requests;

import com.example.moneycare.model.Transaction;
import com.example.moneycare.model.UserAuthEntity;

import java.util.Objects;

public class AddWithdrawRequest {

    public UserAuthEntity srcUser;
    public Transaction userTransaction;

    public AddWithdrawRequest(UserAuthEntity srcUser, Transaction userTransaction) {
        this.srcUser = srcUser;
        this.userTransaction = userTransaction;
    }

    public AddWithdrawRequest() {
    }


    @Override
    public String toString() {
        return "{"
                + "\"srcUser\":" + srcUser
                + ", \"userTransaction\":" + userTransaction
                + "}";
    }
    /*@Override
    public String toString() {
        return "AddWithdrawRequest{" +
                "srcUser=" + srcUser.toString() +
                ", userTransaction=" + userTransaction.toString() +
                '}';
    }*/
}


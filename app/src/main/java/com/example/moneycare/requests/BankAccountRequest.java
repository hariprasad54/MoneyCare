package com.example.moneycare.requests;

import com.example.moneycare.model.BankAccount;
import com.example.moneycare.model.UserAuthEntity;

public class BankAccountRequest {
    public UserAuthEntity srcUser;
    public BankAccount bankAccount;

    public BankAccountRequest(UserAuthEntity srcUser, BankAccount bankAccount) {
        this.srcUser = srcUser;
        this.bankAccount = bankAccount;
    }

    public BankAccountRequest() {
    }

    /**
     * {
     * "srcUser": {
     * "userName": "user1",
     * "password": "pwd1"
     * },
     * "bankAccount": {
     * "accountNo": "3456",
     * "bankName": "SBI",
     * "ifsc": "SBIN34523",
     * "upiId": "456@ybl",
     * "name": "user1 fn"
     * }
     * }
     *
     * @return
     */
    @Override
    public String toString() {
        return "{"
                + "\"srcUser\":" + srcUser
                + ", \"bankAccount\":" + bankAccount
                + "}";
    }
}

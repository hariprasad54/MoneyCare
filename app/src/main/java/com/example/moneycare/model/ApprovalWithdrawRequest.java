package com.example.moneycare.model;

import androidx.annotation.Nullable;

public class ApprovalWithdrawRequest {

    public UserAuthEntity srcUser;
    public Transaction userTransaction;

    public ApprovalWithdrawRequest(UserAuthEntity srcUser, Transaction userTransaction) {
        this.srcUser = srcUser;
        this.userTransaction = userTransaction;
    }

    public ApprovalWithdrawRequest() {
    }
    @Override
    public int hashCode() {
        return this.srcUser.getUserName().hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return  ((ApprovalWithdrawRequest)obj).srcUser.getUserName()
                .equals(((ApprovalWithdrawRequest)this).srcUser.getUserName());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"srcUser\": ").append(srcUser);
        sb.append(", \"userTransaction\": ").append(userTransaction);
        sb.append("}");

        return sb.toString();
    }
}

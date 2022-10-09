package com.example.moneycare.model;


import androidx.annotation.Nullable;

public class ApprovalRequest {
    public UserAuthEntity srcUser;
    public BasicUserEntity targetUser;
    public int amount;

    public UserAuthEntity getSrcUser() {
        return srcUser;
    }

    public void setSrcUser(UserAuthEntity srcUser) {
        this.srcUser = srcUser;
    }

    public BasicUserEntity getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(BasicUserEntity targetUser) {
        this.targetUser = targetUser;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ApprovalRequest(UserAuthEntity srcUser, BasicUserEntity targetUser) {
        this.srcUser = srcUser;
        this.targetUser = targetUser;
    }

    public ApprovalRequest(UserAuthEntity srcUser, BasicUserEntity targetUser, int amount) {
        this.srcUser = srcUser;
        this.targetUser = targetUser;
        this.amount = amount;
    }

    public ApprovalRequest() {
    }

    @Override
    public int hashCode() {
        return this.srcUser.getUserName().hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return  ((ApprovalRequest)obj).srcUser.getUserName()
                .equals(((ApprovalRequest)this).srcUser.getUserName());
    }

    @Override
    public String toString() {
        return "{"
                + "\"srcUser\":" + srcUser
                + ", \"targetUser\":" + targetUser
                + ", \"amount\":\"" + amount + "\""
                + "}";
    }
}

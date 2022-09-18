package com.example.moneycare.model;


import androidx.annotation.Nullable;

public class ApprovalRequest {
    public UserAuthEntity srcUser;
    public BasicUserEntity targetUser;

    public ApprovalRequest(UserAuthEntity srcUser, BasicUserEntity targetUser) {
        this.srcUser = srcUser;
        this.targetUser = targetUser;
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
                + "\"srcUser\":" + srcUser.toString()
                + ", \"targetUser\":" + targetUser.toString()
                + "}";
    }
}

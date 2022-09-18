package com.example.moneycare.requests;

import com.example.moneycare.model.BasicUserEntity;
import com.example.moneycare.model.UserAuthEntity;

public class AddUserRequest {
    public UserAuthEntity srcUser;
    public BasicUserEntity targetUser;

    public AddUserRequest() {
    }

    public AddUserRequest(UserAuthEntity srcUser, BasicUserEntity targetUser) {
        this.srcUser = srcUser;
        this.targetUser = targetUser;
    }

    @Override
    public String toString() {
        return "{"
                + "\"srcUser\":" + srcUser
                + ", \"targetUser\":" + targetUser
                + "}";
    }
    //    @Override
//    public String toString() {
//        return "{" +
//                "srcUser=" + srcUser.toString() +
//                ", targetUser=" + targetUser.toString() +
//                '}';
//    }
}

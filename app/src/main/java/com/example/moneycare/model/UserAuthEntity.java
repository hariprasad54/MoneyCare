package com.example.moneycare.model;

public class UserAuthEntity {
    private String userName;
    private String password;

    public UserAuthEntity() {
    }

    public UserAuthEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public UserAuthEntity setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "\"userName\":\"" + userName + '\"' +
                ", \"password\":\"" + password + '\"' +
                '}';
    }
}

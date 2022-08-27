package com.example.moneycare.model;

public class MemberSuper {

    private String Email;
    private int profileUrl;

    public MemberSuper(String email, int profileUrl) {
        Email = email;
        this.profileUrl = profileUrl;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(int profileUrl) {
        this.profileUrl = profileUrl;
    }
}

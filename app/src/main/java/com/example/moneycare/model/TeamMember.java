package com.example.moneycare.model;

import android.widget.ImageView;

public class TeamMember {

    private String email,phoneNumber;
    private int profileImage;

    public TeamMember() {
    }

    public TeamMember(String email, String phoneNumber, int profileImage) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }
}

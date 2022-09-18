package com.example.moneycare.model;

import androidx.annotation.Nullable;

public class BasicUserEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;

    private String transcationId;

    public String getTranscationId() {
        return transcationId;
    }

    public void setTranscationId(String transcationId) {
        this.transcationId = transcationId;
    }

    public BasicUserEntity(MemberSub user){
        this.email = user.getEmail();;
        this.mobileNo = user.getPhone();
    }
    public BasicUserEntity(String firstName, String lastName, String email, String mobileNo, String transcationId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNo = mobileNo;
        this.transcationId = transcationId;
    }

    public BasicUserEntity(String firstName, String lastName, String mobileNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNo = mobileNo;
    }

    public BasicUserEntity(String firtName, String lastName, String email, String mobileNo) {
        this.firstName = firtName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNo = mobileNo;
    }

    public BasicUserEntity() {
    }

    @Override
    public int hashCode() {
        return this.mobileNo.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        assert obj != null;
        return this.mobileNo.equals(((BasicUserEntity)obj).mobileNo);
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public BasicUserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "{"
                + "\"firstName\":\"" + firstName + "\""
                + ", \"lastName\":\"" + lastName + "\""
                + ", \"email\":\"" + email + "\""
                + ", \"mobileNo\":\"" + mobileNo + "\""
                + ", \"transcationId\":\"" + transcationId + "\""
                + "}";
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }


}

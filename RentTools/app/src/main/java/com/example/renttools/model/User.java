package com.example.renttools.model;

public class User {

    private String uid;
    private String email;
    private String name;
    private String phoneNumber;
    private String location;


    public User(){}


    public User(String email, String name){
        this.email = email;
        this.name = name;
    }

    public User(String uId, String email, String name, String phoneNumber, String location) {
        this.uid = uId;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.location = location;

    }

    public String getUId() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}


package com.example.renttools.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class User {

    private String uid;
    private String email;
    private String name;
    private String phoneNumber;
    private String city;


    public User(){}


    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public User(String uId, String email, String name, String phoneNumber, String city) {
        this.uid = uId;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.city = city;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}


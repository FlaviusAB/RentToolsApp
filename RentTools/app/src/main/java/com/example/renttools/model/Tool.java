package com.example.renttools.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Tool {


    private String manufacturer;
    private String model;
    private String description;
    private double pricePerDay;
    private String uId;

    public Tool() {
    }

    public Tool(String manufacturer, String model, String description, double pricePerDay, String uId) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.uId = uId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}

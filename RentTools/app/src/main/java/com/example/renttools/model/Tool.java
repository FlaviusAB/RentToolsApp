package com.example.renttools.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Tool {


    private String toolId;
    private String manufacturer;
    private String model;
    private String description;
    private double pricePerDay;
    private String userId;

    public Tool() {
    }

    public Tool(String toolId,String manufacturer, String model, String description, double pricePerDay, String userId) {
        this.toolId = toolId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.description = description;
        this.pricePerDay = pricePerDay;
        this.userId = userId;
    }

    public Tool(String manufacturer, String model, String description, double pricePerDay) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.description = description;
        this.pricePerDay = pricePerDay;
    }

    public Tool(String toolId, String manufacturer, String model, String description, double pricePerDay) {
        this.toolId = toolId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.description = description;
        this.pricePerDay = pricePerDay;
    }

    public void setToolId(String toolId) {
        this.toolId = toolId;
    }

    public String getToolId() {
        return toolId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

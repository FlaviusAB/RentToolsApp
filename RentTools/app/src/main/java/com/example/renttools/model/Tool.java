package com.example.renttools.model;


public class Tool {


    private String manufacturer;
    private String model;
    private String description;
    private double pricePerDay;

    public Tool(String manufacturer, String model, String description, double pricePerDay) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.description = description;
        this.pricePerDay = pricePerDay;
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

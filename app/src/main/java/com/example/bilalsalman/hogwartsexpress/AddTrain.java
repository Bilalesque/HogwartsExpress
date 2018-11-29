package com.example.bilalsalman.hogwartsexpress;

public class AddTrain {
    String tainNumber;
    String manufacturer;
    String operator;
    String routeID;
    String businessCapacity;
    String economyCapacity;
    String numberOfCarriages;

    public AddTrain() {
    }

    public AddTrain(String tainNumber, String manufacturer, String operator, String routeID, String businessCapacity, String economyCapacity, String numberOfCarriages) {
        this.tainNumber = tainNumber;
        this.manufacturer = manufacturer;
        this.operator = operator;
        this.routeID = routeID;
        this.businessCapacity = businessCapacity;
        this.economyCapacity = economyCapacity;
        this.numberOfCarriages = numberOfCarriages;
    }

    public String getTainNumber() {
        return tainNumber;
    }

    public void setTainNumber(String tainNumber) {
        this.tainNumber = tainNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRouteID() {
        return routeID;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }

    public String getBusinessCapacity() {
        return businessCapacity;
    }

    public void setBusinessCapacity(String businessCapacity) {
        this.businessCapacity = businessCapacity;
    }

    public String getEconomyCapacity() {
        return economyCapacity;
    }

    public void setEconomyCapacity(String economyCapacity) {
        this.economyCapacity = economyCapacity;
    }

    public String getNumberOfCarriages() {
        return numberOfCarriages;
    }

    public void setNumberOfCarriages(String numberOfCarriages) {
        this.numberOfCarriages = numberOfCarriages;
    }
}
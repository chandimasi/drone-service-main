package com.musalasoft.droneservicemain.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class DroneDTO {
    @Size(max = 100, message="Serial number max length should be 100")
    private String serialNumber;
    private String model;
    @Max(value = 500, message = "Max weight is 500")
    private Double weightLimit;
    private Double batteryCapacity;
    private String state;
    public DroneDTO(){}
    public DroneDTO(String serialNumber, String model, double weightLimit, Double batteryCapacity, String state) {
        super();
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(Double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public Double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

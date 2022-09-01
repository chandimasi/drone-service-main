package com.musalasoft.droneservicemain.dto;

public class BatteryAuditDTO {
    private Long id;
    private Double batteryLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }
}

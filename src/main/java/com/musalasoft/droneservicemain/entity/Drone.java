package com.musalasoft.droneservicemain.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "model")
    private String model;

    @Column(name = "weight_limit")
    private Double weightLimit;

    @Column(name = "battery_capacity")
    private Double batteryCapacity;

    @Column(name = "drone_state")
    private String state;

    @OneToOne(mappedBy = "drone")
    private MedicationLoad medicationLoad;

    @OneToMany(mappedBy = "drone")
    private List<BatteryAudit> batteryAudits = new ArrayList<>();

    public Drone(){}

    public Drone(String serialNumber, String model, double weightLimit, Double batteryCapacity, String state) {
        super();
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public MedicationLoad getMedicationLoad() {
        return medicationLoad;
    }

    public void setMedicationLoad(MedicationLoad medicationLoad) {
        this.medicationLoad = medicationLoad;
    }

    public List<BatteryAudit> getBatteryAudits() {
        return batteryAudits;
    }

    public void setBatteryAudits(List<BatteryAudit> batteryAudits) {
        this.batteryAudits = batteryAudits;
    }
}

package com.musalasoft.droneservicemain.dto;

public class MedicationLoadDTO {
    private String medicationName;
    private Long weight;

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }
}

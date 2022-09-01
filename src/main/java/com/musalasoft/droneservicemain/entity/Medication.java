package com.musalasoft.droneservicemain.entity;

import javax.persistence.*;

@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    private Long weight;

    @Column
    private String code;

    @Column
    private String image;

    @OneToOne
    private MedicationLoad medicationLoad;

    public Medication(){}

    public Medication(String name, Long weight, String code){
        super();
        this.name = name;
        this.weight =weight;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MedicationLoad getMedicationLoad() {
        return medicationLoad;
    }

    public void setMedicationLoad(MedicationLoad medicationLoad) {
        this.medicationLoad = medicationLoad;
    }
}

package com.musalasoft.droneservicemain.service;

import com.musalasoft.droneservicemain.dto.DroneDTO;
import com.musalasoft.droneservicemain.entity.BatteryAudit;
import com.musalasoft.droneservicemain.entity.Drone;
import com.musalasoft.droneservicemain.entity.Medication;
import com.musalasoft.droneservicemain.entity.MedicationLoad;
import com.musalasoft.droneservicemain.exception.DroneNotFoundException;
import com.musalasoft.droneservicemain.exception.MedicationNotFoundException;
import com.musalasoft.droneservicemain.repository.BatteryAuditRepository;
import com.musalasoft.droneservicemain.repository.MedicationLoadRepository;
import com.musalasoft.droneservicemain.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.musalasoft.droneservicemain.repository.DroneRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DroneService {
    @Autowired
    DroneRepository droneRepository;
    @Autowired
    MedicationLoadRepository medicationLoadRepository;
    @Autowired
    MedicationRepository medicationRepository;
    @Autowired
    BatteryAuditRepository batteryAuditRepository;

    public Drone registerDrone(DroneDTO droneDTO) {
        Drone drone = new Drone();
        drone.setSerialNumber(droneDTO.getSerialNumber());
        drone.setModel(droneDTO.getModel());
        drone.setWeightLimit(droneDTO.getWeightLimit());
        drone.setBatteryCapacity(droneDTO.getBatteryCapacity());
        drone.setState(droneDTO.getState());
        droneRepository.save(drone);
        return drone;
    }

    public MedicationLoad loadDroneWithMedication(Long droneId, Long medicationId){
        Drone drone = null;
        try {
            drone = droneRepository.getReferenceById(droneId);
        } catch (Exception e) {
            throw new DroneNotFoundException(String.format("Can't find the drone %s", droneId));
        }

        MedicationLoad existingLoad = getMedicationLoad(drone);
        Medication medication = getMedication(medicationId);
        validateDroneLoad(existingLoad, medication, drone);
        MedicationLoad medicationLoad = new MedicationLoad();
        medicationLoad.setDrone(drone);
        medicationLoad.setMedication(medication);
        medicationLoad.setWeight(medication.getWeight());
        medicationLoadRepository.save(medicationLoad);
        drone.setState("LOADED");
        droneRepository.save(drone);
        return medicationLoad;
    }

    private void validateDroneLoad(MedicationLoad existingLoad, Medication medication, Drone drone){
        if (existingLoad != null) {
            throw new RuntimeException("Drone is already loaded");
        }
        if (drone.getWeightLimit() < medication.getWeight()) {
            throw new RuntimeException("Drone cannot be loaded with more than specified weight limit");
        }
        if (drone.getBatteryCapacity() < 25) {
            throw new RuntimeException("Drone Battery Level is law.");
        }
    }

    private MedicationLoad getMedicationLoad(Drone drone) {
        MedicationLoad medicationLoad = medicationLoadRepository.findByDrone(drone);
        return medicationLoad;
    }

    private Medication getMedication(Long medicationId) {
        Optional<Medication> med = medicationRepository.findById(medicationId);
        if(!med.isPresent()){
            throw new MedicationNotFoundException("Medication specified does not exist");
        }
        return med.get();

    }

    public MedicationLoad getDroneMedicationLoad(Long droneId){
        try {
            Drone drone = droneRepository.getReferenceById(droneId);
            MedicationLoad medicationLoad = medicationLoadRepository.findByDrone(drone);
            return medicationLoad;
        } catch (Exception e) {
            throw new DroneNotFoundException(String.format("Can't find the drone %s", droneId));
        }
    }

    public List<Drone> getAvailableDrones(){
        List<Drone> availableDrones = new ArrayList<>();
        try {
            List<Drone>  droneList = droneRepository.findByState("IDLE");
            if (droneList != null && droneList.size() > 0) {
                for (Drone drone : droneList) {
                    if (drone.getBatteryCapacity() >= 25 ) {
                        availableDrones.add(drone);
                    }
                }
            }
            return availableDrones;
        } catch (Exception e) {
            throw new DroneNotFoundException("Can't find any available drone");
        }
    }

    public BatteryAudit getDroneBatteryLevel(Long droneId){
        try {
            Drone drone = droneRepository.getReferenceById(droneId);
            BatteryAudit batteryAudit = new BatteryAudit();
            batteryAudit.setBatteryLevel(drone.getBatteryCapacity());
            batteryAudit.setDrone(drone);
            batteryAudit.setCreatedAt(java.time.LocalDateTime.now());

            batteryAuditRepository.save(batteryAudit);
            return batteryAudit;
        } catch (Exception e) {
            throw new DroneNotFoundException(String.format("Can't find the drone %s", droneId));
        }
    }
}

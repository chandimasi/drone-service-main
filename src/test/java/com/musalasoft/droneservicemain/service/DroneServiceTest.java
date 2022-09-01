package com.musalasoft.droneservicemain.service;

import com.musalasoft.droneservicemain.dto.DroneDTO;
import com.musalasoft.droneservicemain.entity.Drone;
import com.musalasoft.droneservicemain.entity.Medication;
import com.musalasoft.droneservicemain.entity.MedicationLoad;
import com.musalasoft.droneservicemain.repository.DroneRepository;
import com.musalasoft.droneservicemain.repository.MedicationLoadRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DroneServiceTest {
    @InjectMocks
    private DroneService droneService;
    @Mock
    DroneRepository droneRepository;
    @Mock
    MedicationLoadRepository medicationLoadRepository;

    @Test
    void testRegisterDrone() {

        DroneDTO droneDTO = new DroneDTO("DRN001", "Lightweight", 300.0, 90.0, "IDLE");
        Drone drone = droneService.registerDrone(droneDTO);
        assertEquals(drone.getSerialNumber(), droneDTO.getSerialNumber());
        assertEquals(drone.getWeightLimit(), droneDTO.getWeightLimit());
    }

    @Test
    void testGetDroneMedicationLoad() {

        Drone drone = new Drone("DRN001", "Lightweight", 300.0, 90.0, "IDLE");
        drone.setId(1L);
        Medication medication = new Medication("Calzana", 200L, "Calzana");
        MedicationLoad medicationLoad = new MedicationLoad();
        medicationLoad.setDrone(drone);
        medicationLoad.setMedication(medication);
        medicationLoad.setWeight(medication.getWeight());
        when(droneRepository.getReferenceById(1L)).thenReturn(drone);
        when(medicationLoadRepository.findByDrone(drone)).thenReturn(medicationLoad);
        MedicationLoad medicationLoadResult = droneService.getDroneMedicationLoad(1L);
        assertEquals(medicationLoadResult.getMedication(), medication);
        assertEquals(medicationLoadResult.getWeight(), medication.getWeight());
    }
}
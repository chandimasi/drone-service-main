package com.musalasoft.droneservicemain.controller;

import com.musalasoft.droneservicemain.dto.BatteryAuditDTO;
import com.musalasoft.droneservicemain.dto.DroneDTO;
import com.musalasoft.droneservicemain.dto.MedicationLoadDTO;
import com.musalasoft.droneservicemain.entity.BatteryAudit;
import com.musalasoft.droneservicemain.entity.Drone;
import com.musalasoft.droneservicemain.entity.MedicationLoad;
import com.musalasoft.droneservicemain.service.DroneService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

@RestController
public class DroneController {
    @Autowired
    private DroneService droneService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/drones")
    public ResponseEntity<DroneDTO> registerDrone(@Valid @RequestBody DroneDTO droneDTO) {
        Drone drone = droneService.registerDrone(droneDTO);
        DroneDTO postResponse = modelMapper.map(drone, DroneDTO.class);
        return new ResponseEntity(postResponse, HttpStatus.CREATED);
    }

    @PostMapping("/drones/{droneId}/load/{medicationId}")
    public ResponseEntity<MedicationLoadDTO> loadDroneWithMedicationItems(@PathVariable Long droneId, @PathVariable Long medicationId) {
        MedicationLoad medicationLoad = droneService.loadDroneWithMedication(droneId, medicationId);
        MedicationLoadDTO medicationLoadDTO = modelMapper.map(medicationLoad, MedicationLoadDTO.class);
        return new ResponseEntity(medicationLoadDTO, HttpStatus.OK);
    }

    @GetMapping("/drones/{droneId}/medicationItems")
    public ResponseEntity<MedicationLoadDTO> getDroneMedicationLoad(@PathVariable Long droneId) {
        MedicationLoad medicationLoad = droneService.getDroneMedicationLoad(droneId);

        if(medicationLoad == null){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        MedicationLoadDTO medicationLoadDTO = modelMapper.map(medicationLoad, MedicationLoadDTO.class);
        return new ResponseEntity(medicationLoadDTO, HttpStatus.OK);
    }

    @GetMapping("/availabledrones")
    public ResponseEntity<List<DroneDTO>> getAvailableDrones() {
        List<Drone> droneList = droneService.getAvailableDrones();
        Type listType = new TypeToken<List<DroneDTO>>() {}.getType();
        List<DroneDTO> droneDTOS = modelMapper.map(droneList, listType);
        return ResponseEntity.ok().body(droneDTOS);
    }

    @PostMapping("/batterylevel/{droneId}")
    public ResponseEntity<BatteryAuditDTO> checkDroneBatteryLevel(@PathVariable Long droneId) {
        BatteryAudit batteryAudit = droneService.getDroneBatteryLevel(droneId);
        BatteryAuditDTO batteryDTO = modelMapper.map(batteryAudit, BatteryAuditDTO.class);
        return new ResponseEntity(batteryDTO, HttpStatus.OK);
    }
}

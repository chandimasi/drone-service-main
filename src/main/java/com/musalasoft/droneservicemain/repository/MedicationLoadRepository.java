package com.musalasoft.droneservicemain.repository;

import com.musalasoft.droneservicemain.entity.Drone;
import com.musalasoft.droneservicemain.entity.MedicationLoad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationLoadRepository extends JpaRepository<MedicationLoad, Long> {
    MedicationLoad findByDrone(Drone drone);
}

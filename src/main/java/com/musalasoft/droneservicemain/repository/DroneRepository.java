package com.musalasoft.droneservicemain.repository;

import com.musalasoft.droneservicemain.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Long> {
    List<Drone> findByState(String state);
}

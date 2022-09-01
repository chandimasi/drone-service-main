package com.musalasoft.droneservicemain.repository;

import com.musalasoft.droneservicemain.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    Optional<Medication> findById(Long id);

}

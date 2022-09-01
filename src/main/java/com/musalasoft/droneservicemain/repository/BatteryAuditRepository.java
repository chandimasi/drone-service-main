package com.musalasoft.droneservicemain.repository;

import com.musalasoft.droneservicemain.entity.BatteryAudit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatteryAuditRepository extends JpaRepository<BatteryAudit, Long> {
}

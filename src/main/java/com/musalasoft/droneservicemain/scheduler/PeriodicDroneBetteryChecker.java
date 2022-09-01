package com.musalasoft.droneservicemain.scheduler;

import com.musalasoft.droneservicemain.entity.Drone;
import com.musalasoft.droneservicemain.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@EnableScheduling
@EnableAsync
public class PeriodicDroneBetteryChecker {
    static final Logger log = LoggerFactory.getLogger(PeriodicDroneBetteryChecker.class);
    @Autowired
    private DroneRepository droneRepository;

    @Scheduled(fixedRate = 20000)
    public void batteryCheck() {
        List<Drone> drones = droneRepository.findAll();

        for(Drone drone:drones) {
            log.info("Drone :"+ drone.getSerialNumber() +"  Battery : "+ drone.getBatteryCapacity());
        }

    }
}

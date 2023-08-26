package com.whisper.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    @Query("SELECT s FROM Device s WHERE s.deviceName = ?1")
    Optional<Device> findDeviceByName(String deviceName);
}

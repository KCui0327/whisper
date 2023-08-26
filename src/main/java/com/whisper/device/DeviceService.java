package com.whisper.device;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }
    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }

    public void addNewDevice(Device device) {
        Optional<Device> deviceByName = deviceRepository
                .findDeviceByName(device.getDeviceName());
        if (deviceByName.isPresent()) {
            throw new IllegalStateException("An existing device already exist...");
        }

        Period agePeriod = Period.between(device.getManufacturingDate(), LocalDate.now());
        device.setAge(agePeriod.getYears());
        deviceRepository.save(device);
    }

    public void deleteDevice(Long deviceId) {
        boolean exist = deviceRepository.existsById(deviceId);
        if (!exist) {
            throw new IllegalStateException(
                    "device with ID" + deviceId + " does not exist"
            );
        }
        deviceRepository.deleteById(deviceId);
    }

    @Transactional
    public void updateDevice(Long deviceId, String deviceName, String deviceType) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new IllegalStateException(
                        "device with id " + deviceId + " does not exist"
                ));

        if (deviceName != null && !deviceName.isEmpty() && !Objects.equals(device.getDeviceName(), deviceName)) {
            device.setDeviceName(deviceName);
        }

        if (deviceType != null && !deviceType.isEmpty() && !Objects.equals(device.getDeviceName(), deviceName)) {
            Optional<Device> studentOptional = deviceRepository.findDeviceByName(deviceType);
            if (studentOptional.isPresent()) throw new IllegalStateException("device name taken");
            device.setDeviceName(deviceName);
        }

        Period agePeriod = Period.between(device.getManufacturingDate(), LocalDate.now());
        device.setAge(agePeriod.getYears());
        deviceRepository.save(device);
    }
}

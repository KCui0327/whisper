package com.whisper.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/device")
public class DeviceController {
    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public List<Device> getDevices() {
        return deviceService.getDevices();
    }

    @PostMapping
    public void registerNewDevice(@RequestBody Device device) {
        deviceService.addNewDevice(device);
    }

    @DeleteMapping(path="{deviceId}")
    public void deleteDevice(@PathVariable("deviceId") Long deviceId) {
        deviceService.deleteDevice(deviceId);
    }

    @PutMapping(path = "{deviceId}")
    public void updateDevice(
            @PathVariable("deviceId") Long deviceId,
            @RequestParam(required = false) String deviceName,
            @RequestParam(required = false) String deviceType) {
        deviceService.updateDevice(deviceId, deviceName, deviceType);
    }
}

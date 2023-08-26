package com.whisper.device;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Device {
    @Id
    @SequenceGenerator(
            name = "iot_device_sequence",
            sequenceName = "iot_device_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "iot_device_sequence"
    )

    private long id;
    private String deviceName;
    private String deviceType;
    private LocalDate manufacturingDate;
    @Transient
    private Integer age;

    public Device() {}

    public Device(long id, String deviceName, String deviceType, LocalDate manufacturingDate) {
        this.id = id;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.manufacturingDate = manufacturingDate;
    }

    public Device(String deviceName, String deviceType, LocalDate manufacturingDate, Integer age) {
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.manufacturingDate = manufacturingDate;
        this.age = age;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getDeviceName() { return deviceName; }
    public void setDeviceName(String deviceName) { this.deviceName = deviceName; }
    public String getDeviceType() { return deviceType; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }
    public LocalDate getManufacturingDate() { return manufacturingDate; }
    public void setManufacturingDate(LocalDate manufacturingDate) { this.manufacturingDate = manufacturingDate; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    @Override
    public String toString() {
        return "IoTDevice{" +
                "id=" + id +
                ", deviceName='" + deviceName + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", manufacturingDate=" + manufacturingDate +
                ", age=" + age +
                '}';
    }
}

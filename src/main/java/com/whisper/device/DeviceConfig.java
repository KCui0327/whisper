package com.whisper.device;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.time.Period;

import static java.time.Month.*;

@Configuration
public class DeviceConfig {

    @Bean
    CommandLineRunner commandLineRunner(DeviceRepository repository) {
        return args -> {
            Device amazon_echo = new Device(
                    "Amazon Echo",
                    "Home Automation",
                    LocalDate.of(2000, JANUARY, 3),
                    Period.between(LocalDate.of(2000, JANUARY, 3), LocalDate.now()).getYears()
            );

            Device smart_doorbell = new Device(
                    "Blink",
                    "Security",
                    LocalDate.of(2021, MARCH, 28),
                    Period.between(LocalDate.of(2021, MARCH, 28), LocalDate.now()).getYears()
            );

            repository.saveAll(
                    List.of(amazon_echo, smart_doorbell)
            );
        };
    }
}
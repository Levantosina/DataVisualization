package com.app.measurement;

import com.app.measurement.DAO.MeasurementDAO;
import com.app.measurement.measurement.Measurement;
import com.app.measurement.measurement.MeasurementRepository;
import com.app.measurement.thingSpeak.ThingSpeakService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Levantosina
 */

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner runner(MeasurementRepository measurementRepository, ThingSpeakService thingSpeakService) {
        return args -> {
            float current=1.25f;
            float voltage=220f;

            Measurement measurement=new Measurement(current,voltage);
            measurementRepository.save(measurement);
            System.out.println(measurement);
            thingSpeakService.saveDataToDatabase();

        };
    }
}
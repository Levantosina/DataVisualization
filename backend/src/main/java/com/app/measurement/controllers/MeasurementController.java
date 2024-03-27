package com.app.measurement.controllers;

import com.app.measurement.DTO.MeasurementDTO;
import com.app.measurement.measurement.MeasurementRegistrationRequest;
import com.app.measurement.services.MeasurementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Levantosina
 */

@RestController
@RequestMapping(path="/api/v1/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }



    @GetMapping
    public List<MeasurementDTO> getMeasurements() {
        return measurementService.getAllMeasurements();
    }

    @GetMapping("{measurementId}")
    public MeasurementDTO getMeasurement(@PathVariable("measurementId") Integer measurementId){
        return measurementService.getMeasurement(measurementId);
    }

//    @PostMapping
//    public void registerMeasurement(
//            @RequestBody MeasurementRegistrationRequest measurementRegistrationRequest){
//        measurementService.addMeasurement(measurementRegistrationRequest);
//   }
}
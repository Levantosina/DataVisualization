package com.app.measurement.DTO;

import com.app.measurement.measurement.Measurement;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author Levantosina
 */
@Service
public class MeasurementDTOMapper implements Function<Measurement,MeasurementDTO> {
    @Override
    public MeasurementDTO apply(Measurement measurement) {

        return new MeasurementDTO(
                measurement.getId(),
                measurement.getVoltage(),
                measurement.getCurrent()
        );
    }
}

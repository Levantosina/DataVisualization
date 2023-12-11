package com.app.measurement.services;

import com.app.measurement.DAO.MeasurementDAO;
import com.app.measurement.DTO.MeasurementDTO;
import com.app.measurement.DTO.MeasurementDTOMapper;
import com.app.measurement.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Levantosina
 */
@Service
public class MeasurementService {

    private final MeasurementDAO measurementDAO;
    private final MeasurementDTOMapper measurementDTOMapper;

    public MeasurementService(@Qualifier("measurementJdbc")MeasurementDAO measurementDAO, MeasurementDTOMapper measurementDTOMapper) {
        this.measurementDAO = measurementDAO;
        this.measurementDTOMapper = measurementDTOMapper;
    }

    public List<MeasurementDTO> getAllMeasurements() {
        return measurementDAO.selectAllMeasurements()
                .stream()
                .map(measurementDTOMapper)
                .collect(Collectors.toList());
    }

    public MeasurementDTO getMeasurement(Integer id) {
        return measurementDAO.selectMeasurementById(id)
                .map(measurementDTOMapper)
                .orElseThrow(
                        ()->new ResourceNotFoundException(
                                "Measurement with id [%s] not found".
                                        formatted(id)));
    }
}

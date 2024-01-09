package com.app.measurement.DAO;

import com.app.measurement.measurements.Measurement;

import java.util.List;
import java.util.Optional;

/**
 * @author Levantosina
 */
public interface MeasurementDAO {

    List<Measurement> selectAllMeasurements();

    Optional<Measurement>selectMeasurementById(Integer id);

}

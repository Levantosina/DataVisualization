package com.app.measurement.services;

import com.app.measurement.DAO.MeasurementDAO;
import com.app.measurement.DTO.MeasurementDTO;
import com.app.measurement.DTO.MeasurementDTOMapper;
import com.app.measurement.measurement.Measurement;
import com.app.measurement.measurement.MeasurementRegistrationRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MeasurementServiceTest {

    private MeasurementService underTest;

    @Mock
    private MeasurementDAO measurementDAO;
    private final MeasurementDTOMapper measurementDTOMapper=new MeasurementDTOMapper();



@BeforeEach
    void setUp() {
    underTest=new MeasurementService(measurementDAO,measurementDTOMapper);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllMeasurements() {
    underTest.getAllMeasurements();
    verify(measurementDAO).selectAllMeasurements();
    }

    @Test
    void getMeasurement() {
    long id=1L;
        Measurement measurement=new Measurement(2.1F,3.33F);
        when(measurementDAO.selectMeasurementById((int) id)).thenReturn(Optional.of(measurement));
        MeasurementDTO expected=new MeasurementDTOMapper().apply(measurement);
        MeasurementDTO actual=underTest.getMeasurement((int) id);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void addMeasurement() {
        Measurement measurement=new Measurement(2.1F,3.33F);
        MeasurementRegistrationRequest request=new MeasurementRegistrationRequest(
                measurement.getVoltage(),
                measurement.getCurrent()
        );
        underTest.addMeasurement(request);
        verify(measurementDAO).insertMeasurement(measurement);
    }
}
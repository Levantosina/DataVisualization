package com.app.measurement.AccessService;

import com.app.measurement.measurement.Measurement;
import com.app.measurement.measurement.MeasurementRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MeasurementJDBCDataAccessServiceTest extends AbstractTestContainers {
    private MeasurementJDBCDataAccessService underTest;
    private final MeasurementRowMapper measurementRowMapper = new MeasurementRowMapper();

    @BeforeEach
    void setUp() {

        underTest = new MeasurementJDBCDataAccessService(
                getJdbcTemplate(),
                measurementRowMapper
        );
    }

    @Test
    void selectAllMeasurements() {
        Measurement measurement=new Measurement(2.1F,3.33F);

        underTest.insertMeasurement(measurement);

        List<Measurement> actual=underTest.selectAllMeasurements();

        assertThat(actual).isNotEmpty();
    }


    @Test
    void selectMeasurementById() {

        Measurement measurement=new Measurement(2.1F,3.33F);

        underTest.insertMeasurement(measurement);

        long id= underTest.selectAllMeasurements()
                .stream()
                .map(Measurement::getId)
                .findFirst()
                .orElseThrow();



        Optional<Measurement> actual= underTest.selectMeasurementById((int) id);

        assertThat(actual).isPresent().hasValueSatisfying(c->{
            assertThat(c.getId()).isEqualTo(id);
            assertThat(c.getVoltage()).isEqualTo(measurement.getVoltage());
            assertThat(c.getCurrent()).isEqualTo(measurement.getCurrent());

        });

    }
}
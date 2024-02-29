package com.app.measurement.measurement;


import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MeasurementRowMapperTest {

    @Test
    void mapRow() throws SQLException{

        MeasurementRowMapper measurementRowMapper=new MeasurementRowMapper();

        ResultSet resultSet=mock(ResultSet.class);

        when(resultSet.getLong("id")).thenReturn(2L);
        when(resultSet.getFloat("voltage")).thenReturn(2.11F);
        when(resultSet.getFloat("current")).thenReturn(4.43F);

        Measurement actual=  measurementRowMapper.mapRow(resultSet,1);

        Measurement expectedMeasurement=new Measurement(2L,2.11F,4.43F);


        assertThat(actual).isEqualTo(expectedMeasurement);
    }
}
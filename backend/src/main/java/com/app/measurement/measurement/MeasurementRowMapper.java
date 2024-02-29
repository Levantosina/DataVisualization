package com.app.measurement.measurement;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Levantosina
 */
@Component
public class MeasurementRowMapper implements RowMapper<Measurement> {
    @Override
    public Measurement mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Measurement(
                rs.getLong("id"),
                rs.getFloat("voltage"),
                rs.getFloat("current")
        );
    }
}

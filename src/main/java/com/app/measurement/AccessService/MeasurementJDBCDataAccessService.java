package com.app.measurement.AccessService;




import com.app.measurement.DAO.MeasurementDAO;
import com.app.measurement.measurement.Measurement;
import com.app.measurement.measurement.MeasurementRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Levantosina
 */

@Repository("measurementJdbc")
public class MeasurementJDBCDataAccessService implements MeasurementDAO {
    private final JdbcTemplate jdbcTemplate;
    private final MeasurementRowMapper measurementRowMapper;


    public MeasurementJDBCDataAccessService(JdbcTemplate jdbcTemplate,MeasurementRowMapper measurementRowMapper) {
        this.measurementRowMapper = measurementRowMapper;
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Measurement> selectAllMeasurements() {
        var sql = """
                SELECT id,voltage,current
                FROM measurement
                """;
        return jdbcTemplate.query(sql,measurementRowMapper);
    }



    @Override
    public Optional<Measurement> selectMeasurementById(Integer id) {

        var sql = """
                SELECT id,voltage,current
                FROM measurement where id=?
                """;
        return jdbcTemplate.query(sql,measurementRowMapper,id)
                .stream()
                .findFirst();
    }



    @Override
    public void insertMeasurement(Measurement measurement) {

        var sql= """
                INSERT INTO measurement(voltage,current)
                values(?,?)
                """;

        int result = jdbcTemplate.update(
                sql,
                measurement.getVoltage(),
                measurement.getCurrent());


        System.out.println("result = " + result);

    }
}

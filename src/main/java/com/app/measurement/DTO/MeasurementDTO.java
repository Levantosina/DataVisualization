package com.app.measurement.DTO;

/**
 * @author Levantosina
 */
public record MeasurementDTO (
    Long id,
    Float voltage,
    Float current
) {}

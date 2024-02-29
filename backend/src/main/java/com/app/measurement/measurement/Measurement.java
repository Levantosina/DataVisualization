package com.app.measurement.measurement;

import jakarta.persistence.*;


import java.util.Objects;

/**
 * @author Levantosina
 */

@Entity
@Table(
        name="measurement",
        uniqueConstraints = {
                @UniqueConstraint(name = "measurement_id_unique",
                        columnNames = "id")
        }
)
public class Measurement  {
    @Id
    @SequenceGenerator(
            name = "measurement_id_seq",
            sequenceName = "measurement_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "measurement_id_seq")
    private Long id;

    @Column(nullable = false)
    private Float voltage;

    @Column(nullable = false)
    private Float current;

    public Measurement() {
    }

    public Measurement(Long id,
                       Float voltage,
                       Float current) {
        this.id = id;
        this.voltage = voltage;
        this.current = current;
    }

    public Measurement(Float voltage, Float current) {
        this.voltage = voltage;
        this.current = current;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getVoltage() {
        return voltage;
    }

    public void setVoltage(Float voltage) {
        this.voltage = voltage;
    }

    public Float getCurrent() {
        return current;
    }

    public void setCurrent(Float current) {
        this.current = current;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return Objects.equals(id, that.id) && Objects.equals(voltage, that.voltage) && Objects.equals(current, that.current);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, voltage, current);
    }


    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", voltage='" + voltage + '\'' +
                ", current='" + current + '\'' +
                '}';
    }

}

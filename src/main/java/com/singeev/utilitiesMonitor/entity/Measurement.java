package com.singeev.utilitiesMonitor.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "measurement", indexes = {
        @Index(columnList = "userId", name = "measurement_user_id_index")
})
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "userId", nullable = false)
    @NotNull(message = "UserId should not be empty")
    private Long userId;

    @Column(name = "gas", nullable = false)
    @NotNull(message = "Gas measurement should not be empty")
    @Positive(message = "Gas measurement should be positive")
    @Max(value = 1000, message = "Gas measurement should be less than 1000 q.m.")
    private Double gas;

    @Column(name = "coldWater", nullable = false)
    @NotNull(message = "Cold water measurement should not be empty")
    @Positive(message = "Cold water measurement should be positive")
    @Max(value = 1000, message = "Cold water measurement should be less than 1000 q.m.")
    private Double coldWater;

    @Column(name = "hotWater", nullable = false)
    @NotNull(message = "Hot water measurement should not be empty")
    @Positive(message = "Hot water measurement should be positive")
    @Max(value = 1000, message = "Gas measurement should be less than 1000 q.m.")
    private Double hotWater;

    @Column(name = "dateTime", nullable = false)
    private Instant dateTime;

    public Measurement() {
    }

    public Measurement(Long id, Long userId, Double gas, Double coldWater, Double hotWater, Instant dateTime) {
        this.id = id;
        this.userId = userId;
        this.gas = gas;
        this.coldWater = coldWater;
        this.hotWater = hotWater;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getGas() {
        return gas;
    }

    public void setGas(Double gas) {
        this.gas = gas;
    }

    public Double getColdWater() {
        return coldWater;
    }

    public void setColdWater(Double coldWater) {
        this.coldWater = coldWater;
    }

    public Double getHotWater() {
        return hotWater;
    }

    public void setHotWater(Double hotWater) {
        this.hotWater = hotWater;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Measurement)) return false;
        Measurement that = (Measurement) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(gas, that.gas) &&
                Objects.equals(coldWater, that.coldWater) &&
                Objects.equals(hotWater, that.hotWater) &&
                Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, gas, coldWater, hotWater, dateTime);
    }
}

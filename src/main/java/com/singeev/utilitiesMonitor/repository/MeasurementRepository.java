package com.singeev.utilitiesMonitor.repository;

import com.singeev.utilitiesMonitor.entity.Measurement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeasurementRepository extends CrudRepository<Measurement, Long> {

    List<Measurement> findByUserIdOrderByDateTimeDesc(Long userId);
}

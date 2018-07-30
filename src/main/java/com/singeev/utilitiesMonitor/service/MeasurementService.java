package com.singeev.utilitiesMonitor.service;

import com.singeev.utilitiesMonitor.entity.Measurement;
import java.util.List;

public interface MeasurementService {

    Measurement save(Measurement measurement);

    List<Measurement> findByUserId(Long userId);

}

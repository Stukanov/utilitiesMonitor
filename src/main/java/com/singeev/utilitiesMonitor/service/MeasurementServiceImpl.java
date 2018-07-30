package com.singeev.utilitiesMonitor.service;

import com.singeev.utilitiesMonitor.entity.Measurement;
import com.singeev.utilitiesMonitor.repository.MeasurementRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    @Autowired
    private MeasurementRepository repository;

    @Override
    public Measurement save(Measurement measurement) {
        measurement.setDateTime(Instant.now());
        return repository.save(measurement);
    }

    @Override
    public List<Measurement> findByUserId(Long userId) {
        return repository.findByUserIdOrderByDateTimeDesc(userId);
    }
}

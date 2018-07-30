package com.singeev.utilitiesMonitor.controller;

import com.singeev.utilitiesMonitor.entity.Measurement;
import com.singeev.utilitiesMonitor.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MeasurementController {

    @Autowired
    private MeasurementService service;

    @PostMapping(value = "/measurements", produces = "application/json")
    public Measurement saveNewMeasurement(@Valid @RequestBody Measurement measurement) {
        return service.save(measurement);
    }

    @GetMapping(value = "/users/{userId}/measurements", produces = "application/json")
    public List<Measurement> getAllByUserId(@PathVariable Long userId) {
        return service.findByUserId(userId);
    }
}

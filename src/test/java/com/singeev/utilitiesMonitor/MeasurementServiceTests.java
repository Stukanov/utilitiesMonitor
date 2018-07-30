package com.singeev.utilitiesMonitor;

import com.singeev.utilitiesMonitor.entity.Measurement;
import com.singeev.utilitiesMonitor.service.MeasurementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.singeev.utilitiesMonitor.TestUtil.*;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasurementServiceTests {

	@Autowired
	private MeasurementService service;

	@Test
	public void shouldSaveNewMeasurement() {
		Measurement newMeasurement = TestUtil.createNewMeasurement();

		Measurement measurement = service.save(newMeasurement);
		assertTrue(measurement.getUserId().equals(USER_ID)
				&& measurement.getGas().equals(GAS)
				&& measurement.getColdWater().equals(COLD_WATER)
				&& measurement.getHotWater().equals(HOT_WATER)
				&& measurement.getDateTime() != null);
	}

	@Test
	public void shouldFindByUserId() {
		List<Measurement> retrievedList = service.findByUserId(USER_ID);
		assertTrue(retrievedList != null && retrievedList.size() == 1);

		Measurement retrieved = retrievedList.get(0);
		assertTrue(retrieved.getUserId().equals(USER_ID)
				&& retrieved.getGas().equals(GAS)
				&& retrieved.getColdWater().equals(COLD_WATER)
				&& retrieved.getHotWater().equals(HOT_WATER)
				&& retrieved.getDateTime() != null);
	}
}

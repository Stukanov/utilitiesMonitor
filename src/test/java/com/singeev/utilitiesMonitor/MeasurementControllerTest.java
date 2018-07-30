package com.singeev.utilitiesMonitor;

import com.singeev.utilitiesMonitor.controller.MeasurementController;
import com.singeev.utilitiesMonitor.entity.Measurement;
import com.singeev.utilitiesMonitor.exception.RestErrorHandler;
import com.singeev.utilitiesMonitor.service.MeasurementService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;

import static com.singeev.utilitiesMonitor.TestUtil.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeasurementControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MeasurementService measurementServiceMock;

    @InjectMocks
    private MeasurementController testController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(testController)
                .build();
    }

    @Test
    public void shouldSaveNewMeasurement() throws Exception {
        Measurement measurement = TestUtil.createNewMeasurement();
        Measurement saved = TestUtil.createNewMeasurement();
        saved.setId(1L);
        saved.setDateTime(Instant.now());

        when(measurementServiceMock.save(any(Measurement.class))).thenReturn(saved);

        mockMvc.perform(post("/measurements")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(measurement)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.userId", is((int) USER_ID)))
                .andExpect(jsonPath("$.coldWater", is(COLD_WATER)))
                .andExpect(jsonPath("$.hotWater", is(HOT_WATER)))
                .andExpect(jsonPath("$.dateTime", notNullValue()));
    }

    @Test
    public void shouldReturnAllHistoryByUserId() throws Exception {

        when(measurementServiceMock.findByUserId(USER_ID))
                .thenReturn(Arrays.asList(new Measurement(), new Measurement()));

        mockMvc.perform(get("/users/" + USER_ID + "/measurements"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldValidateRequest() throws Exception {
        Measurement measurement = TestUtil.createNewMeasurement();
        measurement.setUserId(null);

        mockMvc.perform(post("/measurements")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(measurement)))
                .andExpect(status().is4xxClientError());
    }
}

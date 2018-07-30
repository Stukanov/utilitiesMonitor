package com.singeev.utilitiesMonitor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.singeev.utilitiesMonitor.entity.Measurement;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.Charset;

public class TestUtil {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    public static final long USER_ID = 17;
    public static final double GAS = 10.5;
    public static final double COLD_WATER = 20.0;
    public static final double HOT_WATER = 13.7;

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    public static Measurement createNewMeasurement(){
        Measurement newMeasurement = new Measurement();
        newMeasurement.setUserId(USER_ID);
        newMeasurement.setGas(GAS);
        newMeasurement.setColdWater(COLD_WATER);
        newMeasurement.setHotWater(HOT_WATER);
        return newMeasurement;
    }
}

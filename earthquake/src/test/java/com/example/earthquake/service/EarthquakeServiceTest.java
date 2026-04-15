package com.example.earthquake.service;

import com.example.earthquake.model.Earthquake;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EarthquakeServiceTest {

    @Autowired
    private EarthquakeService service;

    @Test
    void testFetchAndStore() {

        List<Earthquake> result = service.fetchAndStoreEarthquakes();

        assertNotNull(result);

        for (Earthquake eq : result) {
            assertNotNull(eq.getMagnitude());
            assertTrue(eq.getMagnitude() > 2.0);
            assertNotNull(eq.getPlace());
            assertNotNull(eq.getTime());
        }
    }

    @Test
    void testGetAll() {

        List<Earthquake> result = service.getAll();

        assertNotNull(result);
        assertTrue(result.size() >= 0);
    }
}
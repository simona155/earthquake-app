package com.example.earthquake.service;

import com.example.earthquake.model.Earthquake;
import com.example.earthquake.repository.EarthquakeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EarthquakeService {

    private final EarthquakeRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String USGS_URL =
            "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson";

    public EarthquakeService(EarthquakeRepository repository) {
        this.repository = repository;
    }

    public List<Earthquake> fetchAndStoreEarthquakes() {

        repository.deleteAll();

        Map<String, Object> response =
                restTemplate.getForObject(USGS_URL, Map.class);

        if (response == null || !response.containsKey("features")) {
            throw new RuntimeException("USGS API is unavailable or returned invalid data");
        }

        List<Map<String, Object>> features =
                (List<Map<String, Object>>) response.get("features");

        List<Earthquake> result = new ArrayList<>();

        long oneHourAgo = Instant.now().minusSeconds(3600).toEpochMilli();

        for (Map<String, Object> feature : features) {

            Map<String, Object> properties =
                    (Map<String, Object>) feature.get("properties");

            Map<String, Object> geometry =
                    (Map<String, Object>) feature.get("geometry");

            if (properties == null || geometry == null) continue;

            Double magnitude = properties.get("mag") != null
                    ? ((Number) properties.get("mag")).doubleValue()
                    : null;

            Long time = properties.get("time") != null
                    ? ((Number) properties.get("time")).longValue()
                    : null;

            if (magnitude == null || time == null) continue;

            if (magnitude <= 2.0) continue;
            if (time < oneHourAgo) continue;

            Earthquake eq = new Earthquake();
            eq.setMagnitude(magnitude);
            eq.setMagType((String) properties.get("magType"));
            eq.setPlace((String) properties.get("place"));
            eq.setTitle((String) properties.get("title"));
            eq.setTime(time);

            result.add(eq);
        }

        repository.saveAll(result);

        return result;
    }

    public List<Earthquake> getAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    public List<Earthquake> getAfterTime(Long time) {
        return repository.findByTimeGreaterThan(time);
    }
}
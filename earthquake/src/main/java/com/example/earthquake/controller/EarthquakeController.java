package com.example.earthquake.controller;

import com.example.earthquake.model.Earthquake;
import com.example.earthquake.service.EarthquakeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/earthquakes")
@CrossOrigin(origins = "*")
public class EarthquakeController {

    private final EarthquakeService service;

    public EarthquakeController(EarthquakeService service) {
        this.service = service;
    }

    @GetMapping("/fetch")
    public List<Earthquake> fetchAndStore() {
        return service.fetchAndStoreEarthquakes();
    }

    @GetMapping
    public List<Earthquake> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping("/after")
    public List<Earthquake> getAfterTime(@RequestParam Long time) {
        return service.getAfterTime(time);
    }
}
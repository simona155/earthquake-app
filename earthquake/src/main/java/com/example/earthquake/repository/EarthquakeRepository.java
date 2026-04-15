package com.example.earthquake.repository;

import com.example.earthquake.model.Earthquake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EarthquakeRepository extends JpaRepository<Earthquake, Long> {

    List<Earthquake> findByTimeGreaterThan(Long time);
}
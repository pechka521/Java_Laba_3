package com.example.lab3.service;

import com.example.lab3.model.Location;
import com.example.lab3.model.SunriseSunset;
import com.example.lab3.repository.LocationRepository;
import com.example.lab3.repository.SunriseSunsetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepo;
    private final SunriseSunsetRepository sunriseSunsetRepo;
    private final Map<String, List<Location>> cache = new HashMap<>();

    @Transactional(readOnly = true)
    public List<Location> getAll() {
        return locationRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Location> getById(Long id) {
        return locationRepo.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Location> getByCoordinates(double lat, double lon) {
        String key = "coords:" + lat + ":" + lon;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        List<Location> result = locationRepo.findByCoordinates(lat, lon);
        cache.put(key, result);
        return result;
    }

    @Transactional(readOnly = true)
    public List<Location> getByDate(String date) {
        String key = "date:" + date;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        List<Location> result = locationRepo.findByDate(date);
        cache.put(key, result);
        return result;
    }

    @Transactional
    public Location create(Location location, List<Long> sunriseSunsetIds) {
        if (sunriseSunsetIds != null) {
            Set<SunriseSunset> sunsets = new HashSet<>(sunriseSunsetRepo.findAllById(sunriseSunsetIds));
            location.setSunriseSunsets(sunsets);
        }
        return locationRepo.save(location);
    }

    @Transactional
    public Optional<Location> update(Long id, Location updated) {
        return locationRepo.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setCountry(updated.getCountry());
            return locationRepo.save(existing);
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (locationRepo.existsById(id)) {
            locationRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
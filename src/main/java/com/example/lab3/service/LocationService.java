package com.example.lab3.service;

import com.example.lab3.model.Location;
import com.example.lab3.model.SunriseSunset;
import com.example.lab3.repository.LocationRepository;
import com.example.lab3.repository.SunriseSunsetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository repository;
    private final SunriseSunsetRepository sunriseSunsetRepository;
    private final Map<String, List<Location>> locationCache;

    @Transactional(readOnly = true)
    public List<Location> getAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Location> getById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Location create(Location location, List<Long> sunriseSunsetIds) {
        if (sunriseSunsetIds != null && !sunriseSunsetIds.isEmpty()) {
            List<SunriseSunset> sunriseSunsets = sunriseSunsetRepository.findAllById(sunriseSunsetIds);
            location.getSunriseSunsets().addAll(sunriseSunsets);
        }
        Location saved = repository.save(location);
        locationCache.clear();
        return saved;
    }

    @Transactional
    public Optional<Location> update(Long id, Location updatedData, List<Long> sunriseSunsetIds) {
        return repository.findById(id).map(location -> {
            location.setName(updatedData.getName());
            location.setCountry(updatedData.getCountry());

            if (sunriseSunsetIds != null) {
                location.getSunriseSunsets().clear();
                List<SunriseSunset> sunriseSunsets = sunriseSunsetRepository.findAllById(sunriseSunsetIds);
                location.getSunriseSunsets().addAll(sunriseSunsets);
            }
            Location saved = repository.save(location);
            locationCache.clear();
            return saved;
        });
    }

    @Transactional
    public boolean delete(Long id) {
        return repository.findById(id).map(location -> {
            repository.delete(location);
            locationCache.clear();
            return true;
        }).orElse(false);
    }

    @Transactional(readOnly = true)
    public List<Location> getLocationsByDate(String date) {
        String cacheKey = "locations_date_" + date;

        if (locationCache.containsKey(cacheKey)) {
            return locationCache.get(cacheKey);
        }

        List<Location> locations = repository.findLocationsBySunriseSunsetDate(date);
        locationCache.put(cacheKey, locations);
        return locations;
    }
}
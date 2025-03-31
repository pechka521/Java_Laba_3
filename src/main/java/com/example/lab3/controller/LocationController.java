package com.example.lab3.controller;

import com.example.lab3.model.Location;
import com.example.lab3.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @GetMapping
    public ResponseEntity<List<Location>> getAll() {
        return ResponseEntity.ok(locationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getById(@PathVariable Long id) {
        return locationService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-coords")
    public ResponseEntity<List<Location>> getByCoordinates(
            @RequestParam double lat,
            @RequestParam double lon) {
        return ResponseEntity.ok(locationService.getByCoordinates(lat, lon));
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<Location>> getByDate(@RequestParam String date) {
        return ResponseEntity.ok(locationService.getByDate(date));
    }

    @PostMapping
    public ResponseEntity<Location> create(
            @RequestBody Location location,
            @RequestParam(required = false) List<Long> sunriseSunsetIds) {
        return ResponseEntity.ok(locationService.create(location, sunriseSunsetIds));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> update(
            @PathVariable Long id,
            @RequestBody Location location) {
        return locationService.update(id, location)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return locationService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
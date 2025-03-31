package com.example.lab3.controller;

import com.example.lab3.model.SunriseSunset;
import com.example.lab3.service.SunriseSunsetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sunrise-sunset")
@RequiredArgsConstructor
public class SunriseSunsetController {
    private final SunriseSunsetService sunriseSunsetService;

    @GetMapping
    public ResponseEntity<List<SunriseSunset>> getAll() {
        return ResponseEntity.ok(sunriseSunsetService.getAll());
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<SunriseSunset>> getByDate(@RequestParam String date) {
        return ResponseEntity.ok(sunriseSunsetService.getByDate(date));
    }
}
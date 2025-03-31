package com.example.lab3.service;

import com.example.lab3.model.SunriseSunset;
import com.example.lab3.repository.SunriseSunsetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SunriseSunsetService {
    private final SunriseSunsetRepository sunriseSunsetRepo;

    @Transactional(readOnly = true)
    public List<SunriseSunset> getAll() {
        return sunriseSunsetRepo.findAll();
    }

    @Transactional(readOnly = true)
    public List<SunriseSunset> getByDate(String date) {
        return sunriseSunsetRepo.findByDate(date);
    }
}
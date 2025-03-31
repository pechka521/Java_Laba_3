package com.example.lab3.repository;

import com.example.lab3.model.SunriseSunset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface SunriseSunsetRepository extends JpaRepository<SunriseSunset, Long> {
    @Query("SELECT ss FROM SunriseSunset ss WHERE ss.date = :date")
    List<SunriseSunset> findByDate(@Param("date") String date);
}
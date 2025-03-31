package com.example.lab3.repository;

import com.example.lab3.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query("SELECT l FROM Location l JOIN l.sunriseSunsets ss WHERE ss.latitude = :lat AND ss.longitude = :lon")
    List<Location> findByCoordinates(@Param("lat") double latitude, @Param("lon") double longitude);

    @Query("SELECT DISTINCT l FROM Location l JOIN l.sunriseSunsets ss WHERE ss.date = :date")
    List<Location> findByDate(@Param("date") String date);
}
package com.system.bike_rental_system.repo;

import com.system.bike_rental_system.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BikeRepo extends JpaRepository<Bike, Integer> {
    @Query(value = "SELECT * FROM bike WHERE category_id = ?1 limit 10", nativeQuery = true)
    Optional<List<Bike>> find10ByCategory(Integer id);

    @Query(value = "SELECT * FROM bike WHERE category_id = ?1", nativeQuery = true)
    Optional<List<Bike>> findAllByCategory(Integer id);

    @Query(value = "SELECT * FROM bike order by rented_number desc limit 5", nativeQuery = true)
    Optional<List<Bike>> findMostRented();
}
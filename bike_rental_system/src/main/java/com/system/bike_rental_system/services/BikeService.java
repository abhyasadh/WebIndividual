package com.system.bike_rental_system.services;

import com.system.bike_rental_system.entity.Bike;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BikeService {
    Bike fetchById(Integer id);
    List<Bike> fetchByCategory(Integer categoryId);
    List<Bike> fetchAllByCategory(Integer categoryId);
    List<Bike> fetchMostRented();
}

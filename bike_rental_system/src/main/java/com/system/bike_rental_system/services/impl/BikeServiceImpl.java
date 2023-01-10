package com.system.bike_rental_system.services.impl;

import com.system.bike_rental_system.entity.Bike;
import com.system.bike_rental_system.repo.BikeRepo;
import com.system.bike_rental_system.services.BikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BikeServiceImpl implements BikeService {
    private final BikeRepo bikeRepo;
    @Override
    public Bike fetchById(Integer id) {
        return bikeRepo.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
    }

    @Override
    public List<Bike> fetchByCategory(Integer categoryId) {
        return bikeRepo.find10ByCategory(categoryId).orElseThrow(()->new RuntimeException("Not Found"));
    }

    @Override
    public List<Bike> fetchAllByCategory(Integer categoryId) {
        return bikeRepo.findAllByCategory(categoryId).orElseThrow(()->new RuntimeException("Not Found"));
    }

    @Override
    public List<Bike> fetchMostRented() {
        return bikeRepo.findMostRented().orElseThrow(()->new RuntimeException("Not Found"));
    }
}

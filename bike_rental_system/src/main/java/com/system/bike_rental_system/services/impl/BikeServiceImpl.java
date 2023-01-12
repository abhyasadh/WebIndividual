package com.system.bike_rental_system.services.impl;

import com.system.bike_rental_system.entity.Bike;
import com.system.bike_rental_system.repo.BikeRepo;
import com.system.bike_rental_system.services.BikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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
        List<Bike> allBikes = bikeRepo.findAllByCategory(categoryId).orElseThrow(()->new RuntimeException("Not Found"));
        List<Bike> requiredBikes = new ArrayList<>();
        for (int i=0; i<8 && i<allBikes.size(); i++){
            requiredBikes.add(allBikes.get(i));
        }
        return requiredBikes;
    }

    @Override
    public List<Bike> similarBikes(Integer categoryId, Integer bikeId) {
        List<Bike> allBikes = bikeRepo.findAllByCategory(categoryId).orElseThrow(()->new RuntimeException("Not Found"));

        for (int i=0; i<allBikes.size(); i++){
            if (Objects.equals(allBikes.get(i).getId(), bikeId)){
                allBikes.remove(i);
                break;
            }
        }

        Collections.shuffle(allBikes);

        List<Bike> requiredBikes = new ArrayList<>();
        for (int i=0; i<4 && i<allBikes.size(); i++){
            requiredBikes.add(allBikes.get(i));
        }
        return requiredBikes;
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

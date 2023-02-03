package com.system.bike_rental_system.repo;

import com.system.bike_rental_system.entity.Bike;
import com.system.bike_rental_system.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {
}

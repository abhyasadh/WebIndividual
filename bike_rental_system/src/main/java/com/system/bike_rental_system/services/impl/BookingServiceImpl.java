package com.system.bike_rental_system.services.impl;

import com.system.bike_rental_system.entity.Booking;
import com.system.bike_rental_system.pojo.BookingPojo;
import com.system.bike_rental_system.repo.BikeRepo;
import com.system.bike_rental_system.repo.BookingRepo;
import com.system.bike_rental_system.repo.UserRepo;
import com.system.bike_rental_system.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepo bookingRepo;
    private final UserRepo userRepo;
    private final BikeRepo bikeRepo;

    @Override
    public void saveBooking(BookingPojo bookingPojo) throws ParseException {
        Booking booking = new Booking();

        if (bookingPojo.getUserId()!=null) {
            booking.setUserId(userRepo.findById(bookingPojo.getUserId()).orElseThrow());
            booking.setBikeId(bikeRepo.findById(bookingPojo.getBikeId()).orElseThrow());
            booking.setBookingDate(Date.valueOf(bookingPojo.getBookingDate()));
            booking.setReleaseDate(Date.valueOf(bookingPojo.getReleaseDate()));
            bookingRepo.save(booking);
        } else {
            System.out.println("No");
        }
    }
}

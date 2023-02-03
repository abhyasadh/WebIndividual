package com.system.bike_rental_system.services;

import com.system.bike_rental_system.pojo.BookingPojo;
import com.system.bike_rental_system.pojo.UserPojo;

import java.security.Principal;
import java.text.ParseException;

public interface BookingService {

    void saveBooking(BookingPojo bookingPojo) throws ParseException;
}

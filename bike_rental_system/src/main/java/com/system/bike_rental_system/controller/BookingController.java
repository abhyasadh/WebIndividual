package com.system.bike_rental_system.controller;

import com.system.bike_rental_system.entity.Booking;
import com.system.bike_rental_system.pojo.BookingPojo;
import com.system.bike_rental_system.services.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.text.ParseException;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/book")
    public String bookBike(@Valid BookingPojo bookingPojo) throws ParseException {
        bookingService.saveBooking(bookingPojo);
        return "redirect:/home";
    }
}

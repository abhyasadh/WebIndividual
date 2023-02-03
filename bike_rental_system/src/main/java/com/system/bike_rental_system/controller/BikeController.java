package com.system.bike_rental_system.controller;

import com.system.bike_rental_system.entity.Bike;
import com.system.bike_rental_system.entity.Booking;
import com.system.bike_rental_system.entity.Category;
import com.system.bike_rental_system.pojo.BikePojo;
import com.system.bike_rental_system.pojo.BookingPojo;
import com.system.bike_rental_system.pojo.UserPojo;
import com.system.bike_rental_system.services.BikeService;
import com.system.bike_rental_system.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class BikeController {
    private final BikeService bikeService;
    private final UserService userService;

    @GetMapping("/bike/{id}")
    public String getBikePage(@PathVariable("id") Integer id, Model model, Principal principal, BookingPojo bookingPojo){
        model.addAttribute("loggedUser", userService.findByEmail(principal.getName()));
        Bike bike = bikeService.fetchById(id);
        model.addAttribute("bike", bike);

        List<Bike> similarBike = bikeService.similarBikes(bike.getCategory().getId(), bike.getId());
        model.addAttribute("similarBikes", similarBike);
        model.addAttribute("booking", bookingPojo);

        return "bikeDetails";
    }
}

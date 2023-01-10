package com.system.bike_rental_system.controller;

import com.system.bike_rental_system.entity.Bike;
import com.system.bike_rental_system.entity.Category;
import com.system.bike_rental_system.pojo.CategoryPojo;
import com.system.bike_rental_system.services.BikeService;
import com.system.bike_rental_system.services.CategoryService;
import com.system.bike_rental_system.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class CategoryController {

    private final CategoryService categoryService;
    private final BikeService bikeService;
    private final UserService userService;

    public List<Bike> getBikeList(@PathVariable("id") Integer categoryId){
        return bikeService.fetchByCategory(categoryId);
    }

    public List<Bike> getAllBikeList(@PathVariable("id") Integer categoryId){
        return bikeService.fetchAllByCategory(categoryId);
    }

    @GetMapping("/home")
    public String getCategoryList(Model model, Principal principal){
        model.addAttribute("loggedUser", userService.findByEmail(principal.getName()));
        List<Category> category = categoryService.fetchAll();

        Map<Category, List<Bike>> map = new HashMap<>();
        for (Category value:category) {
            map.put(value, getBikeList(value.getId()));
        }

        List<Bike> mostRented = bikeService.fetchMostRented();
        model.addAttribute("mostRented", mostRented);

        model.addAttribute("categoryBike", map);

        return "/home";
    }

    @GetMapping("/category/{id}")
    public String getIndividualPage(@PathVariable("id") Integer id, Model model, Principal principal){
        model.addAttribute("loggedUser", userService.findByEmail(principal.getName()));
        Category category = categoryService.fetchById(id);
        model.addAttribute("category", new CategoryPojo(category));

        List<Bike> bikeList = getAllBikeList(id);
        model.addAttribute("bikeList", bikeList);
        return "category";
    }
}

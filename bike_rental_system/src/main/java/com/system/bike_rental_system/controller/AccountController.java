package com.system.bike_rental_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AccountController {
    @GetMapping("/account")
    public String getPage(){
        return "accountDetails";
    }
}
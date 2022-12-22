package com.system.bike_rental_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {

    @GetMapping("/login")
    public String getPage(){
        return "login";
    }

    @GetMapping("/list")
    public String getUserList(Model model){
        return ("user_list");
    }
}

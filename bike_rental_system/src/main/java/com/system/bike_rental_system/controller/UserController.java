package com.system.bike_rental_system.controller;

import com.system.bike_rental_system.entity.User;
import com.system.bike_rental_system.pojo.UserPojo;
import com.system.bike_rental_system.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public String getUserList(Model model){
        List<User> users = userService.fetchAll();
        model.addAttribute("userList", users);

        return ("User/index");
    }

    @GetMapping("/user/create")
    public String getPage(){
        return "user/create";
    }

    @GetMapping("/create")
    public String createUser(){
        return ("User/create");
    }

    @PostMapping("/save")
    public String saveUser(@Valid UserPojo userPojo){
        userService.saveUser(userPojo);
        return "redirect:/user/list";
    }
}

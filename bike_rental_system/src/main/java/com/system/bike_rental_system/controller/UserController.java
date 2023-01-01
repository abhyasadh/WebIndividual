package com.system.bike_rental_system.controller;

import com.system.bike_rental_system.entity.User;
import com.system.bike_rental_system.pojo.UserPojo;
import com.system.bike_rental_system.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/create")
    public String createUser(Model model){
        model.addAttribute("user", new UserPojo());
        return ("User/create");
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model){
        User user = userService.fetchById(id);
        model.addAttribute("user", new UserPojo(user));
        return "/User/create";
    }

    @PostMapping("/save")
    public String saveUser(@Valid UserPojo userPojo){
        userService.saveUser(userPojo);
        return "redirect:/User/index";
    }
}

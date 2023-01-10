package com.system.bike_rental_system.controller;

import com.system.bike_rental_system.entity.User;
import com.system.bike_rental_system.pojo.UserPojo;
import com.system.bike_rental_system.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public String getUserList(Model model){
        List<User> users = userService.fetchAll();
        model.addAttribute("userList", users);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String getPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("user", new UserPojo());
            return "/login";
        }
        return "redirect:/home";
    }

    @PostMapping("/save")
    public String saveUser(@Valid UserPojo userPojo){
        userService.saveUser(userPojo);
        return "redirect:/login";
    }

    @PostMapping("/updateGeneral/{id}")
    public String updateGeneral(@Valid UserPojo userPojo, @RequestParam("image") MultipartFile image){
        userService.updateGeneral(userPojo, image);
        return "redirect:/";
    }

    @PostMapping("/updateDocs/{id}")
    public String updateDocs(@PathVariable Integer id, @RequestParam("citizenshipF") MultipartFile citizenF, @RequestParam("citizenshipB") MultipartFile citizenB, @RequestParam("license") MultipartFile license){
        userService.updateDocs(id, citizenF, citizenB, license);
        return "redirect:/account";
    }

    @GetMapping("/account")
    public String getPage(Model model, Principal principal){
        model.addAttribute("loggedUser", userService.findByEmail(principal.getName()));
        return "accountDetails";
    }

    @GetMapping("/logout")
    public String logout(Authentication authentication){
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.clearContext();
        }
        return "/login";
    }

//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable("id") Integer id){
//        userService.deleteById(id);
//        return "redirect:/user/list";
//    }
}

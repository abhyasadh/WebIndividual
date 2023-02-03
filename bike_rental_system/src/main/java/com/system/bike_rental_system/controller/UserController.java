package com.system.bike_rental_system.controller;

import com.system.bike_rental_system.entity.User;
import com.system.bike_rental_system.pojo.PasswordChangePojo;
import com.system.bike_rental_system.pojo.UserPojo;
import com.system.bike_rental_system.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
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
    public String updateGeneral(@Valid UserPojo userPojo) throws IOException {
        userService.updateGeneral(userPojo);
        return "redirect:/account";
    }

    @PostMapping("/updateDocs/{id}")
    public String updateDocs(@Valid UserPojo userPojo) throws IOException {
        userService.updateDocs(userPojo);
        return "redirect:/account";
    }

    @GetMapping("/account")
    public String getPage(Model model, Principal principal){
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("loggedUser", user);
        model.addAttribute("passwordChangePojo", new PasswordChangePojo());
        return "accountDetails";
    }

    @GetMapping("/logout")
    public String logout(Authentication authentication){
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.clearContext();
        }
        return "/login";
    }

    @PostMapping("/change")
    public String createUser(@Valid @ModelAttribute PasswordChangePojo passwordChangePojo, BindingResult bindingResult,
                             Authentication authentication) {
        if (authentication.isAuthenticated()) {
            passwordChangePojo.setEmail(authentication.getName());
            if (bindingResult.hasErrors()) {
                return "redirect:/account";
            }
            userService.changePassword(passwordChangePojo);
            SecurityContextHolder.clearContext();
        }
        return "redirect:/account";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Principal principal){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches("", userService.findByEmail(principal.getName()).getPassword())){
            userService.deleteAccount(id);
            return "login";
        }
        return "redirect:/account";
    }
}

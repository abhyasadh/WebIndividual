package com.system.bike_rental_system.services.impl;

import com.system.bike_rental_system.entity.User;
import com.system.bike_rental_system.pojo.UserPojo;
import com.system.bike_rental_system.repo.UserRepo;
import com.system.bike_rental_system.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public String saveUser(UserPojo userPojo) {
        User user=new User();
        user.setEmail(userPojo.getEmail());
        user.setFName(userPojo.getFName());
        user.setLName(userPojo.getLName());
        user.setMobile(userPojo.getMobileNo());
        user.setPassword(userPojo.getPassword());
        userRepo.save(user);
        return "created";
    }

    @Override
    public List<User> fetchAll() {
        return userRepo.findAll();
    }
}

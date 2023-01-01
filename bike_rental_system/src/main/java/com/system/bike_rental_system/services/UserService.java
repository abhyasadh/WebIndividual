package com.system.bike_rental_system.services;

import com.system.bike_rental_system.entity.User;
import com.system.bike_rental_system.pojo.UserPojo;

import java.util.List;

public interface UserService {
    String saveUser(UserPojo userPojo);
    List<User> fetchAll();
    User fetchById(Integer id);
}

package com.system.bike_rental_system.services;

import com.system.bike_rental_system.entity.User;
import java.util.List;

public interface UserService {
    List<User> fetchAll();
}

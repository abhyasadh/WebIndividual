package com.system.bike_rental_system.services;

import com.system.bike_rental_system.entity.User;
import com.system.bike_rental_system.pojo.UserPojo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    //Create ------------------------------------------------------
    String saveUser(UserPojo userPojo);


    //Retrieve ----------------------------------------------------
    List<User> fetchAll();
    User fetchById(Integer id);
    UserPojo findByEmail(String email);


    //Update -------------------------------------------------------
    String updateGeneral(UserPojo userPojo, MultipartFile userImage);
    void updateDocs(Integer id, MultipartFile citizenF, MultipartFile citizenB, MultipartFile license);


    //Delete -------------------------------------------------------
    void deleteById(Integer id);
}

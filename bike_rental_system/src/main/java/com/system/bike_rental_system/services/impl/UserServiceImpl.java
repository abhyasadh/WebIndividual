package com.system.bike_rental_system.services.impl;

import com.system.bike_rental_system.entity.User;
import com.system.bike_rental_system.exception.AppException;
import com.system.bike_rental_system.pojo.UserPojo;
import com.system.bike_rental_system.repo.UserRepo;
import com.system.bike_rental_system.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public String saveUser(UserPojo userPojo) {
        User user= new User();
        user.setEmail(userPojo.getEmail());
        user.setFName(userPojo.getFName());
        user.setLName(userPojo.getLName());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userPojo.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);
        return "created";
    }

    public String updateGeneral(UserPojo userPojo, MultipartFile userImage){
        User user= new User();
        user.setId(userPojo.getId());
        user.setFName(userPojo.getFName());
        user.setLName(userPojo.getLName());
        user.setMobileNo(userPojo.getMobileNo());
        user.setAddress(userPojo.getAddress());

        String ImageUrl = saveImage(userImage, "user-images/");
        user.setImage(ImageUrl);

        userRepo.save(user);
        return "updated";
    }

    public void updateDocs(Integer id, MultipartFile citizenFront, MultipartFile citizenBack, MultipartFile license){
        User user = fetchById(id);
        user.setCitizenshipF(saveImage(citizenFront, "user-documents/citizenship-front/"));
        user.setCitizenshipB(saveImage(citizenBack, "user-documents/citizenship-back/"));
        user.setLicense(saveImage(license, "user-documents/license/"));
        user.setStatus("Submitted");
        userRepo.save(user);
    }

    private String saveImage(MultipartFile imageFile, String folder) {
        String fileName = imageFile.getOriginalFilename();
        String filePath = "/src/main/resources/static/images/"+ folder + fileName;
        File dest = new File(filePath);
        try {
            imageFile.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/images/" + fileName;
    }

    @Override
    public List<User> fetchAll() {
        return userRepo.findAll();
    }

    @Override
    public User fetchById(Integer id) {
        return userRepo.findById(id).orElseThrow(()->new RuntimeException("Not Found"));
    }

    @Override
    public UserPojo findByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new AppException("Invalid User email", HttpStatus.BAD_REQUEST));
        return new UserPojo(user);
    }

    @Override
    public void deleteById(Integer id) {
        userRepo.deleteById(id);
    }
}

package com.system.bike_rental_system.services.impl;

import com.system.bike_rental_system.entity.User;
import com.system.bike_rental_system.exception.AppException;
import com.system.bike_rental_system.pojo.PasswordChangePojo;
import com.system.bike_rental_system.pojo.UserPojo;
import com.system.bike_rental_system.repo.EmailCredRepo;
import com.system.bike_rental_system.repo.UserRepo;
import com.system.bike_rental_system.services.UserService;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.IOException;
import java.lang.module.Configuration;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
//    private final JavaMailSender getJavaMailSender;
//    private final EmailCredRepo emailCredRepo;
//    private final ThreadPoolTaskExecutor taskExecutor;
//
//    @Autowired
//    @Qualifier("emailConfigBean")
//    private Configuration emailConfig;
    private final UserRepo userRepo;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "\\images\\user-documents\\";

    @Override
    public void saveUser(UserPojo userPojo) {
        User user= new User();
        user.setEmail(userPojo.getEmail());
        user.setFName(userPojo.getFName());
        user.setLName(userPojo.getLName());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userPojo.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);
    }

    public void updateGeneral(UserPojo userPojo) throws IOException {
        User user= fetchById(userPojo.getId());
        user.setFName(userPojo.getFName());
        user.setLName(userPojo.getLName());
        user.setMobileNo(userPojo.getMobileNo());
        user.setAddress(userPojo.getAddress());

        if(!Objects.equals(userPojo.getImage().getOriginalFilename(), "")){
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY+"profile-picture\\", userPojo.getImage().getOriginalFilename());
            Files.write(fileNameAndPath, userPojo.getImage().getBytes());

            user.setImage("profile-picture\\"+userPojo.getImage().getOriginalFilename());
        }

        if(user.getLicense()!=null &&
                user.getCitizenshipF()!=null &&
                user.getCitizenshipB()!=null)
        {user.setStatus("Submitted");}
        else {
            user.setStatus("Not Submitted");

        }

        userRepo.save(user);
    }

    public void updateDocs(UserPojo userPojo) throws IOException {
        User user = fetchById(userPojo.getId());

        if(!Objects.equals(userPojo.getCitizenshipF().getOriginalFilename(), "")){
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY+"citizenship-front\\", userPojo.getCitizenshipF().getOriginalFilename());
            Files.write(fileNameAndPath, userPojo.getCitizenshipF().getBytes());

            user.setCitizenshipF("citizenship-front\\"+userPojo.getCitizenshipF().getOriginalFilename());
        } else {
            user.setCitizenshipF(null);
        }

        if(!Objects.equals(userPojo.getCitizenshipB().getOriginalFilename(), "")){
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY+"citizenship-back\\", userPojo.getCitizenshipB().getOriginalFilename());
            Files.write(fileNameAndPath, userPojo.getCitizenshipB().getBytes());

            user.setCitizenshipB("citizenship-back\\"+userPojo.getCitizenshipB().getOriginalFilename());
        } else {
            user.setCitizenshipB(null);
        }

        if(!Objects.equals(userPojo.getLicense().getOriginalFilename(), "")){
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY+"license\\", userPojo.getLicense().getOriginalFilename());
            Files.write(fileNameAndPath, userPojo.getLicense().getBytes());

            user.setLicense("license\\"+userPojo.getLicense().getOriginalFilename());
        } else {
            user.setLicense(null);
        }

        if(user.getMobileNo()!=null &&
                user.getAddress()!=null &&
                user.getImage()!=null)
        {user.setStatus("Submitted");}
        else {
            user.setStatus("Rejected");
        }

        userRepo.save(user);
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
    public User findByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new AppException("Invalid User email", HttpStatus.BAD_REQUEST));

        user= User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fName(user.getFName())
                .lName(user.getLName())
                .mobileNo(user.getMobileNo())
                .address(user.getAddress())
                .imageBase64(getImageBase64(user.getImage()))
                .citizenshipFBase64(getImageBase64(user.getImage()))
                .citizenshipBBase64(getImageBase64(user.getCitizenshipB()))
                .license(getImageBase64(user.getLicense()))
                .status(user.getStatus())
                .build();

        return user;
    }

    @Override
    public void deleteAccount(Integer id) {
        userRepo.deleteById(id);
    }

    @Override
    public void changePassword(PasswordChangePojo passwordChangePojo) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<User> optionalUser = userRepo.findByEmail(passwordChangePojo.getEmail());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (encoder.matches(passwordChangePojo.getOldPassword(), user.getPassword())) {
                if (passwordChangePojo.getNewPassword().equals(passwordChangePojo.getRepeatPassword())) {
                    user.setPassword(encoder.encode(passwordChangePojo.getNewPassword()));
                    userRepo.save(user);
                } else {
                    throw new AppException("New Password doesn't match.", HttpStatus.BAD_REQUEST);
                }

            } else {
                throw new AppException("Old Password doesn't match existing password.", HttpStatus.BAD_REQUEST);
            }
        }

    }

    public String getImageBase64(String fileName) {
        if (fileName!=null) {
            String filePath = System.getProperty("user.dir") + "/images/user-documents/";
            File file = new File(filePath + fileName);
            byte[] bytes;
            try {
                bytes = Files.readAllBytes(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return Base64.getEncoder().encodeToString(bytes);
        }
        return null;
    }
//    @Override
//    public void sendEmail() {
//        try {
//            Map<String, String> model = new HashMap<>();
//
//            MimeMessage message = getJavaMailSender.createMimeMessage();
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
//
//            Template template = emailConfig.getTemplate("emailTemp.ftl");
//            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
//
//            mimeMessageHelper.setTo("sendfrom@yopmail.com");
//            mimeMessageHelper.setText(html, true);
//            mimeMessageHelper.setSubject("Registration");
//            mimeMessageHelper.setFrom("sendTo@yopmail.com");
//
//            taskExecutor.execute(new Thread() {
//                public void run() {
//                    getJavaMailSender.send(message);
//                }
//            });
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//    }
}

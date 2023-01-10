package com.system.bike_rental_system.pojo;

import com.system.bike_rental_system.entity.User;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPojo {
    private Integer id;
    private String fName;
    private String lName;
    private String email;
    private String password;
    private String mobileNo;
    private String address;
    private String image;
    private String citizenshipF;
    private String citizenshipB;
    private String license;
    private String status;

    public UserPojo(User user){
        this.id=user.getId();
        this.fName= user.getFName();
        this.lName= user.getLName();
        this.email= user.getEmail();
        this.password= user.getPassword();
        this.mobileNo=user.getMobileNo();
        this.address= user.getAddress();
        this.image=user.getImage();
        this.citizenshipF=user.getCitizenshipF();
        this.citizenshipB=user.getCitizenshipB();
        this.license=user.getLicense();
        this.status=user.getStatus();
    }
}

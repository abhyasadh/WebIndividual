package com.system.bike_rental_system.pojo;

import com.system.bike_rental_system.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public UserPojo(User user){
        this.id=user.getId();
        this.fName= user.getFName();
        this.lName= user.getLName();
        this.email= user.getEmail();
        this.password= user.getPassword();
    }
}

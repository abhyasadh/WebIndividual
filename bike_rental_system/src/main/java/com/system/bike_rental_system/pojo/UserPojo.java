package com.system.bike_rental_system.pojo;

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
    private String mobileNo;


}

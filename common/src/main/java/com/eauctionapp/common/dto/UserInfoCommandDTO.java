package com.eauctionapp.common.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;

@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserInfoCommandDTO implements Serializable {

    @NotEmpty
    @Size(min = 5,max = 30,message = "First Name should have min 5 max 30 characters")
    private String firstName;
    @NotEmpty
    @Size(min = 3,max = 25,message = "Last Name should have min 3 max 25 characters")
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String pin;
    @NotEmpty
    @Size(min = 10,max = 10,message = "Mobile Number should have min 10 max 10 characters")
    private String phone;
    @NotBlank
    @Email(message = "Please enter a valid email",regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w]{2,4}$")
    private String email;
    private UserType userType;
}

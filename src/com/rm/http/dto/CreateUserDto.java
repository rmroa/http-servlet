package com.rm.http.dto;

import lombok.Builder;
import lombok.Value;

import javax.servlet.http.Part;

@Value
@Builder
public class CreateUserDto {

    String firstName;
    String lastName;
    String birthday;
    Part image;
    String country;
    String city;
    String phone;
    String email;
    String password;
    String gender;
}

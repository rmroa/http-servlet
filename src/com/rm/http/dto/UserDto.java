package com.rm.http.dto;

import com.rm.http.entity.Gender;
import com.rm.http.entity.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {

    Long id;
    String firstName;
    String lastName;
    LocalDate birthday;
    String image;
    String country;
    String city;
    String phone;
    String email;
    Role role;
    Gender gender;
}

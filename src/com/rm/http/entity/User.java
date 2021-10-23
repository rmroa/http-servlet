package com.rm.http.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String image;
    private String country;
    private String city;
    private String phone;
    private String email;
    private String password;
    private Role role;
    private Gender gender;

}

package com.rm.http.mapper;

import com.rm.http.dto.CreateUserDto;
import com.rm.http.entity.Gender;
import com.rm.http.entity.User;
import com.rm.http.util.LocalDateFormatter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    private static final String IMAGE_FOLDER = "users\\";
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .image(IMAGE_FOLDER + object.getImage().getSubmittedFileName())
                .country(object.getCountry())
                .city(object.getCity())
                .phone(object.getPhone())
                .email(object.getEmail())
                .password(object.getPassword())
                .gender(Gender.valueOf(object.getGender()))
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }
}

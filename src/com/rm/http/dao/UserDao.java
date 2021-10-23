package com.rm.http.dao;

import com.rm.http.entity.User;
import com.rm.http.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserDao implements Dao<Long, User> {

    private static final UserDao INSTANCE = new UserDao();
    private static final String SAVE_SQL = "" +
            "INSERT INTO users (first_name, last_name, birthday, image, country, city, phone, email, password, gender) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    @SneakyThrows
    public User save(User user) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, user.getFirstName());
            preparedStatement.setObject(2, user.getLastName());
            preparedStatement.setObject(3, user.getBirthday());
            preparedStatement.setObject(4, user.getImage());
            preparedStatement.setObject(5, user.getCountry());
            preparedStatement.setObject(6, user.getCity());
            preparedStatement.setObject(7, user.getPhone());
            preparedStatement.setObject(8, user.getEmail());
            preparedStatement.setObject(9, user.getPassword());
            preparedStatement.setObject(10, user.getGender().name());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getObject("id", Long.class));
            return user;
        }
    }

    @Override
    public void update(User user) {
    }

    @Override
    public boolean delete() {
        return false;
    }
}

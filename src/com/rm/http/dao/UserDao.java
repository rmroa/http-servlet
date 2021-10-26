package com.rm.http.dao;

import com.rm.http.entity.Gender;
import com.rm.http.entity.Role;
import com.rm.http.entity.User;
import com.rm.http.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private static final String FIND_BY_EMAIL_AND_PASSWORD = "" +
            "SELECT id, " +
            "first_name, " +
            "last_name, " +
            "birthday, " +
            "image, country, " +
            "city, " +
            "phone, " +
            "email, " +
            "password, " +
            "role, " +
            "gender " +
            "FROM users " +
            "WHERE email = ? AND password = ?";

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        }
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

    private User buildUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .birthday(resultSet.getDate("birthday").toLocalDate())
                .image(resultSet.getString("image"))
                .country(resultSet.getString("country"))
                .city(resultSet.getString("city"))
                .phone(resultSet.getString("phone"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .role(Role.valueOf(resultSet.getString("role")))
                .gender(Gender.valueOf(resultSet.getString("gender")))
                .build();
    }
}

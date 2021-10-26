package com.rm.http.dao;

import com.rm.http.entity.Manufacturer;
import com.rm.http.util.ConnectionManager;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ManufacturerDao implements Dao<Integer, Manufacturer> {

    private static final ManufacturerDao INSTANCE = new ManufacturerDao();
    private static final String FIND_ALL_SQL = "" +
            "SELECT id, " +
            "brand, " +
            "country " +
            "FROM manufacturer";

    public static ManufacturerDao getInstance() {
        return INSTANCE;
    }

    @Override
    @SneakyThrows
    public List<Manufacturer> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Manufacturer> manufacturers = new ArrayList<>();
            while (resultSet.next()) {
                manufacturers.add(buildManufacturer(resultSet));
            }
            return manufacturers;
        }
    }

    @Override
    public Optional<Manufacturer> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Manufacturer save(Manufacturer entity) {
        return null;
    }

    @Override
    public void update(Manufacturer entity) {

    }

    @Override
    public boolean delete() {
        return false;
    }

    private Manufacturer buildManufacturer(ResultSet resultSet) throws SQLException {
        return Manufacturer.builder()
                .id(resultSet.getInt("id"))
                .brand(resultSet.getString("brand"))
                .country(resultSet.getString("country"))
                .build();
    }
}

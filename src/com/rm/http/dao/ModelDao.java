package com.rm.http.dao;

import com.rm.http.entity.Model;
import com.rm.http.entity.ModelDriveUnit;
import com.rm.http.entity.ModelEngineType;
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
public class ModelDao implements Dao<Long, Model> {

    private static final ModelDao INSTANCE = new ModelDao();
    private static final String FIND_BY_MANUFACTURER_ID_SQL = "" +
            "SELECT id, " +
            "model, " +
            "manufacturer_id, " +
            "production_year, " +
            "vehicle_type_id, " +
            "transmission, " +
            "drive_unit, " +
            "engine_type, " +
            "current_mileage, " +
            "price " +
            "FROM models " +
            "WHERE manufacturer_id = ?";

    public static ModelDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Model> findAll() {
        return null;
    }

    @SneakyThrows
    public List<Model> findByManufacturerId(Long manufacturerId) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_MANUFACTURER_ID_SQL)) {
            preparedStatement.setLong(1, manufacturerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Model> models = new ArrayList<>();
            while (resultSet.next()) {
                models.add(buildModel(resultSet));
            }
            return models;
        }
    }

    @Override
    public Optional<Model> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Model save(Model entity) {
        return null;
    }

    @Override
    public void update(Model model) {

    }

    @Override
    public boolean delete() {
        return false;
    }

    private Model buildModel(ResultSet resultSet) throws SQLException {
        return Model.builder()
                .id(resultSet.getLong("id"))
                .model(resultSet.getString("model"))
                .manufacturerId(resultSet.getLong("manufacturer_id"))
                .productionYear(resultSet.getDate("production_year").toLocalDate())
                .vehicleTypeId(resultSet.getLong("vehicle_type_id"))
                .transmission(resultSet.getString("transmission"))
                .driveUnit(ModelDriveUnit.valueOf(resultSet.getString("drive_unit")))
                .engineType(ModelEngineType.valueOf(resultSet.getString("engine_type")))
                .currentMileage(resultSet.getLong("current_mileage"))
                .price(resultSet.getBigDecimal("price"))
                .build();
    }
}


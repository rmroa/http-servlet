package com.rm.http.service;

import com.rm.http.dao.ModelDao;
import com.rm.http.dto.ModelDto;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ModelService {

    private static final ModelService INSTANCE = new ModelService();

    private final ModelDao modelDao = ModelDao.getInstance();

    public List<ModelDto> findByManufacturerId(Long manufacturerId) {
        return modelDao.findByManufacturerId(manufacturerId).stream()
                .map(model -> ModelDto.builder()
                        .id(model.getId())
                        .description(String.format("%s -- %s -- %s -- %s", model.getModel(), model.getProductionYear(),
                                model.getCurrentMileage(), model.getPrice()))
                        .build()
                )
                .collect(toList());
    }

    public static ModelService getInstance() {
        return INSTANCE;
    }
}

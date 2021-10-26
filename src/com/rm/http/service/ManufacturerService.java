package com.rm.http.service;

import com.rm.http.dao.ManufacturerDao;
import com.rm.http.dto.ManufacturerDto;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ManufacturerService {

    private static final ManufacturerService INSTANCE = new ManufacturerService();

    private final ManufacturerDao manufacturerDao = ManufacturerDao.getInstance();

    public static ManufacturerService getInstance() {
        return INSTANCE;
    }

    public List<ManufacturerDto> findAll() {
        return manufacturerDao.findAll().stream()
                .map(manufacturer -> ManufacturerDto.builder()
                        .id(manufacturer.getId())
                        .description(String.format("%s - %s", manufacturer.getBrand(), manufacturer.getCountry()))
                        .build()
                )
                .collect(toList());
    }
}


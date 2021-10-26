package com.rm.http.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Model {

    private Long id;
    private String model;
    private Long manufacturerId;
    private LocalDate productionYear;
    private Long vehicleTypeId;
    private String transmission;
    private ModelDriveUnit driveUnit;
    private ModelEngineType engineType;
    private Long currentMileage;
    private BigDecimal price;
}

package com.rm.http.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ModelDto {

    Long id;
    String description;
}

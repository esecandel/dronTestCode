package com.idealista.scandel.testcode.service;

import com.idealista.scandel.testcode.config.UrbanizationConfig;
import com.idealista.scandel.testcode.domain.Direction;

public interface IdealistaService {

    String getUrbanizationId(double coordenadaX, double coordenadaY);
    String getAdjacent(String originUrbanizationId, Direction direction);

    void setUrbanizationConfig(UrbanizationConfig urbanizationConfig);
}

package com.idealista.scandel.testcode.service;

import java.util.Set;

public interface UrbanizationService {


    Set<String> obtenerUrbanizaciones(double coordenadaX, double coordenadaY, int range);

    void setIdealistaService(IdealistaService idealistaService);
}

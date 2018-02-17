package com.idealista.scandel.testcode.controller;

import com.idealista.scandel.testcode.service.UrbanizationService;
import com.idealista.scandel.testcode.service.impl.UrbanizationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "/search")
public class DroneController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrbanizationServiceImpl.class);

    @Autowired
    private UrbanizationService urbanizationService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<String> searchUrbanizations(@RequestBody @Valid DroneRequest droneRequest, HttpServletResponse response) {

        LOGGER.info("Petición recibida: {}", droneRequest);

        return urbanizationService.obtenerUrbanizaciones(droneRequest.getCoordenadaX(),
                droneRequest.getCoordenadaY(), droneRequest.getRange());
    }

}

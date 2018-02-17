package com.idealista.scandel.testcode.service.impl;

import com.idealista.scandel.testcode.config.UrbanizationConfig;
import com.idealista.scandel.testcode.domain.Direction;
import com.idealista.scandel.testcode.service.IdealistaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class IdealistaServiceImpl implements IdealistaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdealistaServiceImpl.class);

    @Autowired
    private UrbanizationConfig urbanizationConfig;

    /**
     * Método obtenerIdentificadorUrbanización(coordenadaX, coordenadaY) = identificadorUrbanización.
     * <p>
     * Dadas unas coordenadas, devuelve el identificador de la urbanización
     * en la que están encerradas dichas coordenadas.
     *
     * @param latitude  latitude coordinate
     * @param longitude longitude coordinate
     * @return identificador de la urbanización situada en esas coordenadas
     */
    @Override
    public String getUrbanizationId(double latitude, double longitude) {
        String urbId = randomUrbanization();
        LOGGER.info("Urbanization found in ({}, {}) = {}", latitude, longitude, urbId);
        return urbId;
    }

    /**
     * Método obtenerAdyacente(identificadorUrbanizaciónOrigen, dirección) = identificadorAdyacente.
     * <p>
     * Dado un identificador y la dirección de adyacencia devuelve el identificador de la
     * urbanización adyacente.
     *
     * @param originUrbanizationId identifier of origin urbanization
     * @param direction            adjacent urbanization direction to search
     * @return identificador de la urbanización adyacente
     */
    @Override
    public String getAdjacent(String originUrbanizationId, Direction direction) {
        String urbId = randomUrbanization();
        LOGGER.info("Urbanization {} to {} = {}", direction, originUrbanizationId, urbId);
        return urbId;
    }

    /**
     * Método que genera una salida aleatoria de entre las urbanizaciones creadas
     *
     * @return urbanización aleatoria
     */
    private String randomUrbanization() {
        return urbanizationConfig.getListIds()
                .get(new Random().nextInt(urbanizationConfig.getListIds().size()));
    }

    @Override
    public void setUrbanizationConfig(UrbanizationConfig urbanizationConfig) {
        this.urbanizationConfig = urbanizationConfig;
    }
}

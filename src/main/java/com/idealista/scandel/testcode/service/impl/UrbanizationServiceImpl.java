package com.idealista.scandel.testcode.service.impl;

import com.idealista.scandel.testcode.domain.Direction;
import com.idealista.scandel.testcode.service.IdealistaService;
import com.idealista.scandel.testcode.service.UrbanizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UrbanizationServiceImpl implements UrbanizationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrbanizationServiceImpl.class);


    @Autowired
    private IdealistaService idealistaService;

    /**
     * Búsqueda recursiva de urbanizaciones adyacentes, comenzando por una coordenada, y
     * a través de un rango que determina la distancia máxima desde el origen
     * @param coordenadaX coordenada del eje de abcisas
     * @param coordenadaY coordenada del eje de ordenadas
     * @param range distancia a recorrer por el dron
     * @return Conjunto con los identificadores de las urbanizaciones encontradas
     */
    @Override
    public Set<String> obtenerUrbanizaciones(double coordenadaX, double coordenadaY, int range) {
        LOGGER.info("Empieza la búsqueda por ({}, {}) en un rango de {}", coordenadaX, coordenadaY, range);
        String originUrbanizationId = idealistaService.getUrbanizationId(coordenadaX, coordenadaY);
        Set<String> urbanizationSetResult =  this.obtenerUrbanizaciones(originUrbanizationId, range);
        LOGGER.info("La búsqueda ha finalizaado con {} urbanizaciones: {}", urbanizationSetResult.size(), urbanizationSetResult);
        return  urbanizationSetResult;
    }

    /**
     * Método recursivo que devuelve todas las urbanizaciones adyacentes si el rango es mayor que 0,
     * o el identificador de la urbanización origen si el rango es 0.
     * @param originUrbanizationId urbanización de origen
     * @param range distancia a recorrer que va disminuyendo en cada iteración
     * @return Conjunto con los identificadores de las urbanizaciones encontradas
     */
    private Set<String> obtenerUrbanizaciones(String originUrbanizationId, int range) {
        Set<String> urbanizationSetResult = new HashSet<>();
        LOGGER.info("Estoy en {}, a {} de distancia", originUrbanizationId, range);

        if (range == 0) {
            urbanizationSetResult.add(originUrbanizationId);
        } else {
            getAllAdjacent(originUrbanizationId).parallelStream()
                    .forEach(urb -> urbanizationSetResult.addAll(this.obtenerUrbanizaciones(urb, range - 1)));
        }

        return urbanizationSetResult;
    }

    /**
     * Método que devuelve un listado con TODAS las urbanizaciones adyacentes a una origen, incluida ésta.
     * Para ello busca las urbanizaciones superior, inferior, izquierda y derecha, así como sus cuatro diagonales
     *
     * @param originUrbanizationId identificador de la urbanización origen
     * @return listado con TODAS las urbanizaciones adyacentes a una origen
     */
    private Set<String> getAllAdjacent(String originUrbanizationId) {
        Set<String> urbanizationSet = new HashSet<>();
        urbanizationSet.add(originUrbanizationId);

        String urbanizationUp = idealistaService.getAdjacent(originUrbanizationId, Direction.ARRIBA);
        urbanizationSet.add(urbanizationUp);
        String urbanizationDown = idealistaService.getAdjacent(originUrbanizationId, Direction.ABAJO);
        urbanizationSet.add(urbanizationDown);
        String urbanizationLeft = idealistaService.getAdjacent(originUrbanizationId, Direction.IZQUIERDA);
        urbanizationSet.add(urbanizationLeft);
        String urbanizationRight = idealistaService.getAdjacent(originUrbanizationId, Direction.DERECHA);
        urbanizationSet.add(urbanizationRight);

        String urbanizationUpLeft;
        String urbanizationUpRight;
        String urbanizationDownLeft;
        String urbanizationDownRight;

        if (urbanizationUp != null) {
            urbanizationUpLeft = idealistaService.getAdjacent(urbanizationUp, Direction.IZQUIERDA);
            urbanizationSet.add(urbanizationUpLeft);
            urbanizationUpRight = idealistaService.getAdjacent(urbanizationUp, Direction.DERECHA);
            urbanizationSet.add(urbanizationUpRight);
        } else if (urbanizationLeft != null && urbanizationRight != null) {
            urbanizationUpLeft = idealistaService.getAdjacent(urbanizationLeft, Direction.ARRIBA);
            urbanizationSet.add(urbanizationUpLeft);
            urbanizationUpRight = idealistaService.getAdjacent(urbanizationRight, Direction.ARRIBA);
            urbanizationSet.add(urbanizationUpRight);
        }

        if (urbanizationDown != null) {
            urbanizationDownRight = idealistaService.getAdjacent(urbanizationDown, Direction.DERECHA);
            urbanizationSet.add(urbanizationDownRight);
            urbanizationDownLeft = idealistaService.getAdjacent(urbanizationDown, Direction.IZQUIERDA);
            urbanizationSet.add(urbanizationDownLeft);
        } else if (urbanizationLeft != null && urbanizationRight != null) {
            urbanizationDownRight = idealistaService.getAdjacent(urbanizationRight, Direction.ABAJO);
            urbanizationSet.add(urbanizationDownRight);
            urbanizationDownLeft = idealistaService.getAdjacent(urbanizationLeft, Direction.ABAJO);
            urbanizationSet.add(urbanizationDownLeft);
        }

        LOGGER.info("Encontradas {} adyacentes: {}", urbanizationSet.size(), urbanizationSet);
        return urbanizationSet;
    }

    @Override
    public void setIdealistaService(IdealistaService idealistaService) {
        this.idealistaService = idealistaService;
    }
}

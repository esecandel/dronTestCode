package com.idealista.scandel.testcode.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuración para recoger la lista de urbanizaciones
 */
@Configuration
@ConfigurationProperties(prefix = "urbanization")
@EnableConfigurationProperties
public class UrbanizationConfig {

    private List<String> listIds = new ArrayList<>();

    public List<String> getListIds() {
        return listIds;
    }

    public void setListIds(List<String> listIds) {
        this.listIds = listIds;
    }
}

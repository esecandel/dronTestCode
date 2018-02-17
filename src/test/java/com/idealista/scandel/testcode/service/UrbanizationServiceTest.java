package com.idealista.scandel.testcode.service;

import com.idealista.scandel.testcode.config.UrbanizationConfig;
import com.idealista.scandel.testcode.service.impl.IdealistaServiceImpl;
import com.idealista.scandel.testcode.service.impl.UrbanizationServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"test"})
@DirtiesContext
public class UrbanizationServiceTest {
    @Autowired
    private UrbanizationConfig urbanizationConfig;

    private IdealistaService idealistaService = new IdealistaServiceImpl();
    private UrbanizationService urbanizationService = new UrbanizationServiceImpl();

    @Before
    public void setup(){
        urbanizationService.setIdealistaService(idealistaService);
        idealistaService.setUrbanizationConfig(urbanizationConfig);
    }

    @Test
    public void obtenerUrbanizacionesTest(){
        Assert.assertNotNull(urbanizationService.obtenerUrbanizaciones(1.2345D, 6.7890D, 2));
    }

}

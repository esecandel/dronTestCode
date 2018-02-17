package com.idealista.scandel.testcode.service;

import com.idealista.scandel.testcode.config.UrbanizationConfig;
import com.idealista.scandel.testcode.domain.Direction;
import com.idealista.scandel.testcode.service.impl.IdealistaServiceImpl;
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
public class IdealistaServiceTest {

    @Autowired
    private UrbanizationConfig urbanizationConfig;

    private IdealistaService idealistaService = new IdealistaServiceImpl();

    @Before
    public void setup(){
        idealistaService.setUrbanizationConfig(urbanizationConfig);
    }

    @Test
    public void getUrbanizationIdTest(){
        Assert.assertNotNull(idealistaService.getUrbanizationId(1.2345D, 6.7890D));
    }

    @Test
    public void getAdjacentTest(){
        Assert.assertNotNull(idealistaService.getAdjacent("idUrbanization0001", Direction.ABAJO));
    }
}

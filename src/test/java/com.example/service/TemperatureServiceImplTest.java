package com.example.service;

import com.example.dao.TemperatureDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by antonsakhno on 22.03.17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
public class TemperatureServiceImplTest {

    @Autowired
    private TemperatureService temperatureService;
    @Autowired
    private TemperatureDAO temperatureDAOMock;


    @Test
    public void testGetLastHourTemperature() {
        when(temperatureDAOMock.readTemperatureForPeriod(any(), any())).thenReturn(Arrays.asList(20.0, -20.0, 30.9, 15.8, -3.0));
        double average = temperatureService.getAvarageTemperatureForLastHour();
        assertEquals(8.74, average);
    }

}

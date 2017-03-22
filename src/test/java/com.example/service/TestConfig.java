package com.example.service;

import com.example.dao.TemperatureDAO;
import com.example.service.impl.TemperatureServiceImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by antonsakhno on 22.03.17.
 */
@Configuration
public class TestConfig {

    @Bean
    public TemperatureService temperatureService() {
        return new TemperatureServiceImpl();
    }

    @Bean
    public TemperatureDAO temperatureDAO(){
        return Mockito.mock(TemperatureDAO.class);
    }
}

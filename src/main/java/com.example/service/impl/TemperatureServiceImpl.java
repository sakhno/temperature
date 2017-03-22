package com.example.service.impl;

import com.example.dao.TemperatureDAO;
import com.example.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.time.temporal.ChronoUnit.HOURS;

/**
 * Created by antonsakhno on 21.03.17.
 */
@Service
public class TemperatureServiceImpl implements TemperatureService {

    private static final long TEMPERATURE_UPDATE_TIME = 1000 * 10;
    private static final double MIN_TEMPERATURE = -40.0;
    private static final double MAX_TEMPERATURE = 40.0;

    @Autowired
    private TemperatureDAO temperatureDAO;

    @Override
    public double getAvarageTemperatureForLastHour() {
        LocalDateTime timeFrom = LocalDateTime.now().minus(1, HOURS);
        LocalDateTime timeto = LocalDateTime.now();
        List<Double> temperatures = temperatureDAO.readTemperatureForPeriod(timeFrom, timeto);
        double sum = 0.0;
        for (Double temp: temperatures) {
            sum += temp;
        }
        return sum / temperatures.size();
    }

    @Scheduled(fixedRate = TEMPERATURE_UPDATE_TIME)
    @Override
    public void updateTemperature() {
        double temperature = ThreadLocalRandom.current().nextDouble(MIN_TEMPERATURE, MAX_TEMPERATURE);
        temperatureDAO.saveCurrentTemperature(temperature);
    }
}

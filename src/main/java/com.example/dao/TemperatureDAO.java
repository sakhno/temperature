package com.example.dao;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by antonsakhno on 21.03.17.
 */
public interface TemperatureDAO {
    List<Double> readTemperatureForPeriod(LocalDateTime timeFrom, LocalDateTime timeTo);
    void saveCurrentTemperature(double temperature);
}

package com.example.controller;

import com.example.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by antonsakhno on 21.03.17.
 */
@RestController
public class AjaxController {

    @Autowired
    private TemperatureService temperatureService;

    @RequestMapping(value = "ajax/temperature", method = RequestMethod.GET)
    public double getRatingLeaders() {
        return temperatureService.getAvarageTemperatureForLastHour();
    }
}

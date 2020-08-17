package com.openclassroom.safetynet.controller;


import com.openclassroom.safetynet.model.Foyer;
import com.openclassroom.safetynet.service.StationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class FloodController {
Logger logger = LoggerFactory.getLogger(FloodController.class);
    @Autowired
    StationService stationService;

    @GetMapping("/flood/stations")
    public List<Foyer> getfoyer(@RequestParam("stations") List<String> stations) {
        logger.info("request List of foyer in list of stations :" + stations);
        List<Foyer> foyer = stationService.getFoyerListForStations(stations);
        logger.info("request List of foyer in list of stations :" + foyer);
        return foyer;
    }

}

package com.openclassroom.safetynet.controller;


import com.openclassroom.safetynet.model.Foyer;
import com.openclassroom.safetynet.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class FloodController {

    @Autowired
    StationService stationService;

    @GetMapping("/flood/stations")
        public List<Foyer> getfoyer(@RequestParam("stations") List<String> stations) {
            return stationService.getfoyer(stations);
        }

}

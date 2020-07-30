package com.openclassroom.safetynet.controller;


import com.openclassroom.safetynet.model.ResponsePersonWithStatistic;
import com.openclassroom.safetynet.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class FirestationController {

    @Autowired
    FirestationService firestationService;

    @GetMapping("/firestation")
    public ResponsePersonWithStatistic getPersonsWithStatistics(@RequestParam("stationNumber") Integer stationNumber) throws IOException {
        return firestationService.getPersonsWithStatistics(stationNumber);
    }
}









package com.openclassroom.safetynet.controller;


import com.openclassroom.safetynet.model.Firestation;
import com.openclassroom.safetynet.model.ResponsePersonWithStatistic;
import com.openclassroom.safetynet.service.FirestationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
public class FirestationController {

Logger logger = LoggerFactory.getLogger(FirestationController.class);
    @Autowired
    FirestationService firestationService;

    @GetMapping("/firestation")
    public ResponsePersonWithStatistic getPersonsWithStatistics(@RequestParam("stationNumber") Integer stationNumber) throws IOException {
        logger.info("request personsWithStatistic in station number : " +  stationNumber);
        ResponsePersonWithStatistic responsePersonWithStatistic = firestationService.getPersonsWithStatistics(stationNumber);
        logger.info(" personsWithStatistic in station number : " +  stationNumber + "is : " + responsePersonWithStatistic);
        return responsePersonWithStatistic;

    }

    @GetMapping("firestations") //cette get methode pour faciliter le test avec postman firestations avec s
    public List<Firestation> getFirestations(){
        logger.info("request list of Firestaions : ");
        List<Firestation> firestations = firestationService.listFirestations();
        logger.info("list of Firestaions : " + firestations);
        return firestations;
    }

    @PostMapping("/firestation")
     public void addFairestation(@RequestBody Firestation firestation) {
        logger.info("adding new firestation " + firestation);
        firestationService.addFirestation(firestation);
    }

    @PutMapping("/firestation/{address}")
    public void updateFirestation(@RequestBody Firestation firestation, @PathVariable String address) {
        logger.info("updating firestation in address : " + address + "firestation become :"+ firestation);
        firestationService.updateFirestation(firestation, address);
    }

    @DeleteMapping("/firestation/{address}")
    public void deleteFirestation(@PathVariable String address) {
        logger.info("deleting  firestation in address : " + address);
        firestationService.deleteFirestation(address);
    }

}









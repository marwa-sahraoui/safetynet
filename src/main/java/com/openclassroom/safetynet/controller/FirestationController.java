package com.openclassroom.safetynet.controller;


import com.openclassroom.safetynet.model.Firestation;
import com.openclassroom.safetynet.model.ResponsePersonWithStatistic;
import com.openclassroom.safetynet.service.FirestationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
public class FirestationController {

    @Autowired
    FirestationService firestationService;

    @GetMapping("/firestation")
    public ResponsePersonWithStatistic getPersonsWithStatistics(@RequestParam("stationNumber") Integer stationNumber) throws IOException {
        return firestationService.getPersonsWithStatistics(stationNumber);
    }

    @GetMapping("firestations") //cette get methode pour faciliter le test avec postman firestations avec s
    public List<Firestation> getFirestations(){
        return firestationService.ListFirestations();
    }

    @PostMapping("/firestation/{Address}")
     public void addFairestation(@RequestBody Firestation firestation,@PathVariable String Address) {
        firestationService.addFirestation(firestation, Address);
    }

    @PutMapping("/firestation/{Address}")
    public void updateFirestation(@RequestBody Firestation firestation,@PathVariable String Address) {
        firestationService.updateFirestation(firestation, Address);
    }

    @DeleteMapping("/firestation/{Address}")
    public void deleteFirestation(@RequestBody Firestation firestation,@PathVariable String Address) {
        firestationService.deleteFirestation(firestation, Address);
    }

}









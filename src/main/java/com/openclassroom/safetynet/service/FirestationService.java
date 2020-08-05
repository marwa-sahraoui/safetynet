package com.openclassroom.safetynet.service;


import com.openclassroom.safetynet.model.Firestation;
import com.openclassroom.safetynet.model.Medicalrecord;
import com.openclassroom.safetynet.model.Person;
import com.openclassroom.safetynet.model.ResponsePersonWithStatistic;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.time.temporal.ChronoUnit.YEARS;

@Service
public class FirestationService {

    @Autowired
    private JsonDataStructureService jsonDataStructureService;

    private Logger logger = Logger.getLogger(FirestationService.class.getName());

    public ResponsePersonWithStatistic getPersonsWithStatistics(Integer stationNumber) throws IOException {

        ResponsePersonWithStatistic toReturn = new ResponsePersonWithStatistic();

        List<Firestation> firestations = new ArrayList<>();

        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();

        for (Firestation f : jsonDataStructure.getFirestations()) {
            if (f.getStation().equals(String.valueOf(stationNumber))) {
                firestations.add(f);
            }
        }

        for (
                Firestation firestation : firestations) {
            for (Person p : jsonDataStructure.getPersons()) {
                if (firestation.getAddress().equals(p.getAddress())) {
                    toReturn.getPerson().add(p);
                    if (isPersonAdult(jsonDataStructure, p)) {
                        toReturn.setNumberAdult(toReturn.getNumberAdult() + 1);
                    } else {
                        toReturn.setNumberChild(toReturn.getNumberChild() + 1);
                    }
                }
            }

        }

        return toReturn;
        
    }

    private boolean isPersonAdult(JsonDataStructure jsonDataStructure, Person p) {
        for (Medicalrecord x : jsonDataStructure.getMedicalrecords()) {
            if (x.getFirstName().equals(p.getFirstName()) && x.getLastName().equals(p.getLastName())) {
                String date = x.getBirthdate();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDate dateTime = LocalDate.parse(date, formatter);

                if (YEARS.between(dateTime, LocalDate.now()) > 18) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

}

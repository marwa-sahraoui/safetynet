package com.openclassroom.safetynet.service;


import com.openclassroom.safetynet.model.*;
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

       // regrouper les firestations ayant le même nombre de station
        for (Firestation f : jsonDataStructure.getFirestations()) {
            if (f.getStation().equals(String.valueOf(stationNumber))) {
                firestations.add(f);
            }
        }
        // collecter les personnes ayant les mêmes adressess que celle présente dans les firestations
        // extraire les noms/prénoms/adresses/téléphones de ces personnes
        //et faire le compte des personnes enfants et adultes
        for (Firestation firestation : firestations) {
            for (Person p : jsonDataStructure.getPersons()) {
                if (firestation.getAddress().equals(p.getAddress())) {
                   //constructeur créé dans la classe personne contenant ces 4 attributs spécifiques
                   Person personWithAddressAndPhone = new Person(p.getFirstName(),p.getLastName(),
                           p.getAddress(),p.getPhone());
                    toReturn.getPerson().add(personWithAddressAndPhone);
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

    /**
     *
     * @param jsonDataStructure
     * @param p
     * @return true si la personne est adule >18ans
     */
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


    public List<Firestation> listFirestations() {
        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();
        return jsonDataStructure.getFirestations();
    }


    public void addFirestation(Firestation firestation) {
        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();
        jsonDataStructure.getFirestations().add(firestation);
    }


    public void updateFirestation(Firestation firestation, String address) {
        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();
        for (int i = 0; i < jsonDataStructure.getFirestations().size(); i++) {
            Firestation f = jsonDataStructure.getFirestations().get(i);
            if (f.getAddress().equals(address)) {
                if (firestation.getAddress() == null) {
                    firestation.setAddress(address);
                }
                jsonDataStructure.getFirestations().set(i, firestation);
                return;
            }
        }
    }

    public void deleteFirestation(String address) {
        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();
        jsonDataStructure.getFirestations().removeIf(f -> f.getAddress().equals(address));
    }
}

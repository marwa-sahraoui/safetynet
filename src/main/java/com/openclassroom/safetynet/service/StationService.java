package com.openclassroom.safetynet.service;


import com.openclassroom.safetynet.model.*;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.time.temporal.ChronoUnit.YEARS;

@Service
public class StationService {

    @Autowired
    private JsonDataStructureService jsonDataStructureService;


    public String stats(@RequestParam("address") String address) {

        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();
        String x = new String();

        for (Firestation f : jsonDataStructure.getFirestations()) {
            for (Person p : jsonDataStructure.getPersons()) {

                if (f.getAddress().equals(address) && (f.getAddress().equals(p.getAddress()))) {
                    x = f.getStation(); // pour eviter d'avoir le nombre de station répetée je fais le break
                    break;
                }

            }

        }
        return x;
    }

    public List<Foyer> getfoyer(List<String> stations) {

        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();

        List<Firestation> StationsUtilisateur = new ArrayList<>();

        List<Foyer> foyers = new ArrayList<>();

        for (String id : stations) {
            for (Firestation firestation : jsonDataStructure.getFirestations()) {
                if (firestation.getStation().equals(id)) {
                    StationsUtilisateur.add(firestation);
                }
            }
        }
        for (Firestation firestation : StationsUtilisateur) {
            for (Medicalrecord m : jsonDataStructure.getMedicalrecords()) {
                for (Person person : jsonDataStructure.getPersons()) {
                    if (person.getFirstName().equals(m.getFirstName()) && person.getLastName().equals(m.getLastName())
                            && (person.getAddress()).equals(firestation.getAddress())) {

                        String date = m.getBirthdate();

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                        LocalDate dateTime = LocalDate.parse(date, formatter);

                        YEARS.between(dateTime, LocalDate.now());
                        int age = (int) YEARS.between(dateTime, LocalDate.now());
                        m.getMedications();
                        m.getAllergies();

                        PersonWithMedicalRecord personWithMedicalRecord = new PersonWithMedicalRecord(person.getFirstName(),
                                person.getLastName(), person.getPhone(), age, m.getMedications(), m.getAllergies());

                        boolean dejaExistant = false;
                        for(Foyer foyer: foyers){
                            if(foyer.getAddress().equals(person.getAddress())){
                                foyer.getPersonWithMedicalRecords().add(personWithMedicalRecord);
                                dejaExistant =true;
                            }
                        }
                        if(dejaExistant != true){
                            Foyer foyer = new Foyer(firestation.getStation(),firestation.getAddress(), new ArrayList<>());
                            foyer.getPersonWithMedicalRecords().add(personWithMedicalRecord);
                            foyers.add(foyer);
                        }
                    }
                }
            }

        }

        return foyers;
    }
}
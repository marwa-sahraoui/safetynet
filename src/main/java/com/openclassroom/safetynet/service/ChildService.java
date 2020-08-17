package com.openclassroom.safetynet.service;

import com.openclassroom.safetynet.model.Medicalrecord;
import com.openclassroom.safetynet.model.Person;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.YEARS;


@Service
public class ChildService {

    @Autowired
    private JsonDataStructureService jsonDataStructureService;

    public List<Person> childrenPerAddress(String address) throws IOException {

        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();

        List<Person> personAdress = new ArrayList<>();
        List<Person> y = new ArrayList<>();

        for (Person pe : jsonDataStructure.getPersons()) {
            if (pe.getAddress().equals(address)) {
                personAdress.add(pe);
            }
        }

        for (Medicalrecord x : jsonDataStructure.getMedicalrecords()) {
            for (Person p : personAdress) {
                if (x.getFirstName().equals(p.getFirstName()) && x.getLastName().equals(p.getLastName())) {

                    String date = x.getBirthdate();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate dateTime = LocalDate.parse(date, formatter);

                    if ((YEARS.between(dateTime, LocalDate.now()) <= 18)) {
                        y.add(p);
                    }
                }

            }
        }

        return y;
    }

}

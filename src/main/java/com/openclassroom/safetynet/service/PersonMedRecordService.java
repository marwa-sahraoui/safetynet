package com.openclassroom.safetynet.service;


import com.openclassroom.safetynet.model.*;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.YEARS;


@Service
public class PersonMedRecordService {

    @Autowired
    private JsonDataStructureService jsonDataStructureService;

    public List<PersonWithMedicalRecord> getpersonMedRecord( String address) {

        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();

        List<Person> result = new ArrayList<>();

        for (Firestation f : jsonDataStructure.getFirestations()) {
            for (Person p : jsonDataStructure.getPersons()) {

                if (f.getAddress().equals(address) && (f.getAddress().equals(p.getAddress()) )) {
                    result.add(p);

                }

            }
        }

        List<PersonWithMedicalRecord> total = new ArrayList<>();
        for (Medicalrecord m : jsonDataStructure.getMedicalrecords()) {
            for (Person person : result) {
                if (person.getFirstName().equals(m.getFirstName()) && person.getLastName().equals(m.getLastName()) ) {

                    String date = m.getBirthdate();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate dateTime = LocalDate.parse(date, formatter);

                    YEARS.between(dateTime, LocalDate.now());
                    int age = (int) YEARS.between(dateTime, LocalDate.now());

                    PersonWithMedicalRecord personWithMedicalRecords = new PersonWithMedicalRecord(m.getFirstName(), person.getLastName(), person.getPhone(), age, m.getMedications(), m.getAllergies());
                    System.out.println(personWithMedicalRecords);

                    total.add(personWithMedicalRecords);
                    break;
                }
            }
        }
        return total;
    }
}


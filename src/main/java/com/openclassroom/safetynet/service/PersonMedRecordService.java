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
    /**
     *
     * @param address
     * @return liste des personnes avec ses medicalrecords:N/p/tel/age/medications/allergies
     */
    public List<PersonWithMedicalRecord> getpersonMedRecord(String address) {

        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();
        List<Person> result = new ArrayList<>();

       // regrouper les personnes ayant une même adresse vu q'une station peut regrouper plus qu'une seule adresse
        for (Firestation f : jsonDataStructure.getFirestations()) {
            for (Person p : jsonDataStructure.getPersons()) {

                if (f.getAddress().equals(address) && (f.getAddress().equals(p.getAddress()))) {
                    result.add(p);
                }
            }
        }

        List<PersonWithMedicalRecord> total = new ArrayList<>();
        for (Medicalrecord m : jsonDataStructure.getMedicalrecords()) {
            for (Person person : result) {
                if (person.getFirstName().equals(m.getFirstName()) && person.getLastName().equals(m.getLastName())) {

                    String date = m.getBirthdate();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate dateTime = LocalDate.parse(date, formatter);

                    YEARS.between(dateTime, LocalDate.now());
                    int age = (int) YEARS.between(dateTime, LocalDate.now());

                    //regrouper la liste des personnes(total) avec les infos suivants: nom ,prenom, phone,age,medication,allergies
                    PersonWithMedicalRecord personWithMedicalRecords = new PersonWithMedicalRecord(m.getFirstName(), person.getLastName(),
                            person.getPhone(), age, m.getMedications(), m.getAllergies());

                    total.add(personWithMedicalRecords);
                    // pour eviter d'avoir une liste  répetée on fais le break
                    break;
                }
            }
        }
        return total;
    }
}


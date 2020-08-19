package com.openclassroom.safetynet.service;

import com.openclassroom.safetynet.model.ChildInfo;
import com.openclassroom.safetynet.model.Medicalrecord;
import com.openclassroom.safetynet.model.Person;
import com.openclassroom.safetynet.model.PersonWithMedicalRecord;
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

    /**
     *
     * @param address
     * @return liste des enfants nom/prénon/age/liste des personnes habitants à la même adresse
     * @throws IOException
     */
    public List<ChildInfo> childrenPerAddress(String address) throws IOException {

        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();

        List<Person> personAdress = new ArrayList<>();
        List<ChildInfo> y = new ArrayList<>();

        Person pName = new Person();

        // récupérer les personnes habitants à une adresse donnée
        for (Person pe : jsonDataStructure.getPersons()) {
            if (pe.getAddress().equals(address)) {
                 pName = new Person(pe.getFirstName(), pe.getLastName());
                personAdress.add(pName);
            }
        }


        /**
         * pour ces personnes habitants à l'adresse donné on va récupérer leur age à partir de la date de naissance
         * et construire une liste des enfants ayant un age <=18 ainsi que la liste des autres personnes habitant à la
         * même adresse
         */
        for (Medicalrecord x : jsonDataStructure.getMedicalrecords()) {
            for (Person p : personAdress) {
                if (x.getFirstName().equals(p.getFirstName()) && x.getLastName().equals(p.getLastName())) {

                    String date = x.getBirthdate();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate dateTime = LocalDate.parse(date, formatter);

                    int age = (int) YEARS.between(dateTime, LocalDate.now());
                    if (age <= 18) {
                        ChildInfo childInfo = new ChildInfo(p.getFirstName(), p.getLastName(), age, personAdress);
                        y.add(childInfo);

                    }
                }

            }
        }

        return y;
    }

}

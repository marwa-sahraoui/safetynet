package com.openclassroom.safetynet.service;

import com.openclassroom.safetynet.model.*;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.YEARS;


@Service
public class ChildService {

    @Autowired
    private JsonDataStructureService jsonDataStructureService;

    /**
     * @param address
     * @return liste des enfants nom/prénon/age/liste des personnes habitants à la même adresse
     * @throws IOException
     */
    public ChildInfoAndfamilyMembre getChildInfoAndfamilyMembre(String address) throws IOException {

        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();

        List<Person> personAdress = new ArrayList<>();
        List<ChildInfo> childInfoList = new ArrayList<>();

        // récupérer les personnes habitants à une adresse donnée
        for (Person pe : jsonDataStructure.getPersons()) {
            if (pe.getAddress().equals(address)) {
                personAdress.add(new Person(pe.getFirstName(), pe.getLastName()));
            }
        }

        /**
         * pour ces personnes habitants à l'adresse donné on va récupérer leur age à partir de la date de naissance
         * et construire une liste des enfants ayant un age <=18 ainsi que la liste des autres personnes habitant à la
         * même adresse
         */
        for (Medicalrecord medicalrecord : jsonDataStructure.getMedicalrecords()) {
            for (Person p : personAdress) {
                if (medicalrecord.getFirstName().equals(p.getFirstName()) && medicalrecord.getLastName().equals(p.getLastName())) {

                    String date = medicalrecord.getBirthdate();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate dateTime = LocalDate.parse(date, formatter);

                    int age = (int) YEARS.between(dateTime, LocalDate.now());
                    if (age <= 18) {
                        ChildInfo childInfo = new ChildInfo(p.getFirstName(), p.getLastName(), age);
                        childInfoList.add(childInfo);
                    }
                }
            }
        }
        //dans le cas ou on n'a pas d'enfant l url sera vide
        if(childInfoList.isEmpty()){
            return null;
        }
        //pour eviter d'avoir une répétition des enfants dans la liste des habitants de même addresse
        for (ChildInfo childInfo : childInfoList) {
            personAdress.remove(new Person(childInfo.getFirstName(), childInfo.getLastName()));
        }

        return new ChildInfoAndfamilyMembre(childInfoList, personAdress);
    }
}

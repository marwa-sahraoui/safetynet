package com.openclassroom.safetynet.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassroom.safetynet.model.*;
import com.openclassroom.safetynet.service.PersonMedRecordService;
import org.apache.catalina.valves.PersistentValve;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.time.temporal.ChronoUnit.YEARS;

public class ReadJSon {
    private static Logger logger = LogManager.getLogger("ReadJSon");

    public static void main(String[] args) throws IOException {

        String fileName = "json/data.json";
        ClassLoader classLoader = new ReadJSon().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());


        // Read File Content
        String content = new String(Files.readAllBytes(file.toPath()));

        //
        ObjectMapper mapper = new ObjectMapper();
        JsonDataStructure jsonDataStructure = mapper.readValue(content, JsonDataStructure.class);

//        // restcontroller 4
        List<Person> result = new ArrayList<>();

        List<Firestation> firestations = new ArrayList<>();
        String x = new String();

//        for (Firestation f : jsonDataStructure.getFirestations()) {
//            for (Person p : jsonDataStructure.getPersons()) {
//
//                if (f.getAddress().equals("1509 Culver St") && (f.getAddress().equals(p.getAddress()))) {
//                   result.add(p);
//                 x= f.getStation();
//                // System.out.println(x);
//
//                }
//
//            }
//
//        }
        //restcontrollerxxxxxx

        List<Firestation> stationsUtilisateur = new ArrayList<>();

        List<Foyer> foyers = new ArrayList<>();

        List<String> stationsId = new ArrayList<>();
        stationsId.add("2");

        for (String id : stationsId) {
            for (Firestation firestation : jsonDataStructure.getFirestations()) {
                if (firestation.getStation().equals(id)) {
                    stationsUtilisateur.add(firestation);
                }
            }

            for (Firestation firestation : stationsUtilisateur) {
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
                            //                      System.out.println(personWithMedicalRecord);

                            //Foyer foyer = new Foyer(firestation.getStation(), firestation.getAddress(),new ArrayList<>());

                            boolean dejaExiste;
                            for (Foyer foyer : foyers) {
                                if (person.getAddress().equals(foyer.getAddress())) {
                                    foyer.getPersonWithMedicalRecords().add(personWithMedicalRecord);
                                    dejaExiste = true;
                                }
                                if (dejaExiste = false) {
                                    Foyer foyer1 = new Foyer(firestation.getStation(), firestation.getAddress(), new ArrayList<>());
                                    foyer1.getPersonWithMedicalRecords().add(personWithMedicalRecord);
                                    foyers.add(foyer1);

                                }
                                System.out.println(foyers);
                            }

                        }
                    }
                }
            }
        }
    }
}



//
//            for (Firestation f : jsonDataStructure.getFirestations()) {
//                if (f.getStation().equals("3")) {
//                Addresses.add(f.getAddress());
//                }
//            }
//            System.out.println(Addresses);

        //    }
















package com.openclassroom.safetynet.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassroom.safetynet.model.Firestation;
import com.openclassroom.safetynet.model.Medicalrecord;
import com.openclassroom.safetynet.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ReadJSon {
    private static Logger logger = LogManager.getLogger("ReadJSon");

    public static void main(String[] args) throws IOException {

        String fileName = "json/data.json";
        ClassLoader classLoader = new ReadJSon().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        //File is found
        System.out.println("File Found : " + file.exists());

        // Read File Content
        String content = new String(Files.readAllBytes(file.toPath()));

        //
        ObjectMapper mapper = new ObjectMapper();
        JsonDataStructure jsonDataStructure = mapper.readValue(content, JsonDataStructure.class);

        // parcourir les personnes ayant la meme firestation
        List<Person> result = new ArrayList<>();

        List<Firestation> firestations = new ArrayList<>();

        for (Firestation f : jsonDataStructure.getFirestations()) {
            if (f.getStation().equals("3")) {
                firestations.add(f);
            }
        }

        for (Firestation firestation : firestations) {
            for (Person p : jsonDataStructure.getPersons()) {
                if (firestation.getAddress().equals(p.getAddress())) {
                    result.add(p);

                }
            }
        }
        System.out.println("exp" + result.toString());



    }


    }


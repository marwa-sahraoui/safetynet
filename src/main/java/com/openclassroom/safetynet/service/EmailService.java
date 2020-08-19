package com.openclassroom.safetynet.service;

import com.openclassroom.safetynet.model.Person;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JsonDataStructureService jsonDataStructureService;

    /**
     *
     * @param city
     * @return liste des emails des personnes habitants à une ville donnée
     */
    public List<String> emails(String city) {

        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();

        List<String> emails = new ArrayList<>();

        for (Person p : jsonDataStructure.getPersons()) {
            if (p.getCity().equals(city)) {
                emails.add(p.getEmail());
            }
        }
        return emails;
    }
}

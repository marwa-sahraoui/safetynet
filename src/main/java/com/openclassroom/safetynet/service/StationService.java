package com.openclassroom.safetynet.service;


import com.openclassroom.safetynet.model.Firestation;
import com.openclassroom.safetynet.model.Person;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Service
public class StationService {

    @Autowired
    private JsonDataStructureService jsonDataStructureService;

    public String stats(@RequestParam("address") String address)  {

        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();
        String x = new String();

        for (Firestation f : jsonDataStructure.getFirestations()) {
            for (Person p : jsonDataStructure.getPersons()) {

                if (f.getAddress().equals(address) && (f.getAddress().equals(p.getAddress()))) {
                    x= f.getStation(); // pour eviter d'avoir le nombre de station répetée je fais le break
                    break;
                }

            }

        }
        return x;
    }
}

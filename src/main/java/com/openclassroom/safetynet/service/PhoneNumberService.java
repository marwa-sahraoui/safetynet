package com.openclassroom.safetynet.service;


import com.openclassroom.safetynet.model.Firestation;
import com.openclassroom.safetynet.model.Person;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneNumberService {

    @Autowired
    private JsonDataStructureService jsonDataStructureService;

    public List<String> getPersonsPhoneNumberByFirestation(Integer station) {
        List<Firestation> firestations = new ArrayList<>();

        List<String> phone = new ArrayList<>();
        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();

        for (Firestation f : jsonDataStructure.getFirestations()) {
            if (f.getStation().equals(String.valueOf(station))) {
                firestations.add(f);
            }
        }

        for (Firestation firestation : firestations) {
            for (Person p : jsonDataStructure.getPersons()) {
                if (firestation.getAddress().equals(p.getAddress())) {
                    phone.add(p.getPhone());
                }
            }
        }

        return phone;
    }
}

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
    /**
     *
     * @param station
     * @return liste des numéro de téléphones de personnes appartenant à une station donnée
     *
     */
    public List<String> getPersonsPhoneNumberByFirestation(Integer station) {
        List<Firestation> firestations = new ArrayList<>();

        List<String> phone = new ArrayList<>();
        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();
        /**
        // récupérer les firestations ayant une starion donnée
        // puis on fait associée les personnes ayant les mêmes adresses que ces firestations
        //puis on récupéres les num de téléphones de ces personnes
         */
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

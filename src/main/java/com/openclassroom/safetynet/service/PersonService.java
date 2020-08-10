package com.openclassroom.safetynet.service;

import com.openclassroom.safetynet.model.Person;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private JsonDataStructureService jsonDataStructureService;

    public void addPerson(Person person) {

        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();
        jsonDataStructure.getPersons().add(person);
    }


    public List<Person> listPerson() {
        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();
        return jsonDataStructure.getPersons();
    }


    public void updatePerson(Person person, String firstName, String lastName) {
        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();
        for(int i=0; i< jsonDataStructure.getPersons().size(); i++){
            Person p = jsonDataStructure.getPersons().get(i);
            if(p.getFirstName().equals(firstName)&&(p.getLastName().equals(lastName))){
                jsonDataStructure.getPersons().set(i,person);
                return;
            }
        }
    }


    public void deletePerson(Person person, String firstName, String lastName) {
        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();
        jsonDataStructure.getPersons().removeIf(p->p.getFirstName().equals(firstName) && p.getLastName().equals(lastName));
    }
}

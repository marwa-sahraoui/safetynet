package com.openclassroom.safetynet.service;

import com.openclassroom.safetynet.model.Medicalrecord;
import com.openclassroom.safetynet.model.Person;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest

class PersonServiceTest {
    @Mock
    JsonDataStructureService jsonDataStructureService;

    @InjectMocks
    PersonService  personService;
    static JsonDataStructure jsonDataStructureExp;

    @BeforeEach
    void init() {
        jsonDataStructureExp = JsonDataStructureExp.getJsonData();
    }

    @Test
    void listPerson() {
     when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);
        assertEquals(6,personService.listPerson().size());
    }

    @Test
    void addPersonVerifiyingSizeIs7() {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);
        Person person = new Person();
        person.setFirstName("James");
        person.setLastName("blunt");
        person.setAddress("14 Rue Hoch");
        person.setCity("Lille");
        person.setEmail("Jaames@gmail.com");
        person.setPhone("454554");
        personService.addPerson(person);
        assertEquals(7, jsonDataStructureService.getJsonDataStructure().getPersons().size());
        assertEquals("James", jsonDataStructureService.getJsonDataStructure().getPersons().get(6).getFirstName());
        assertNotEquals("Zack", jsonDataStructureService.getJsonDataStructure().getPersons().get(6).getFirstName());
    }

    @Test
    void updatePersonModifiyingHerEmail() {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);

        Person updatePerson  = new Person();
        updatePerson.setEmail("CaBeans@gmail.com");

        personService.updatePerson(updatePerson,"Carla","Beans");
        assertEquals( "CaBeans@gmail.com",jsonDataStructureService.getJsonDataStructure().getPersons().get(0).getEmail());
        assertEquals( "Carla",jsonDataStructureService.getJsonDataStructure().getPersons().get(0).getFirstName());
        assertNotEquals("Carla@gmail.com", jsonDataStructureService.getJsonDataStructure().getMedicalrecords().get(0).getBirthdate());

    }

    @Test
    void deletePersonNamedChrisMartin() {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);

        assertEquals(1,
                jsonDataStructureService.getJsonDataStructure()
                        .getMedicalrecords()
                        .stream()
                        .filter(person-> person.getFirstName().equals("Chris")
                                && person.getLastName().equals("Martin"))
                        .count()
        );
        personService.deletePerson("Chris","Martin");

        assertEquals(0,
                jsonDataStructureService.getJsonDataStructure()
                        .getPersons()
                        .stream()
                        .filter(person-> person.getFirstName().equals("Chris")
                                && person.equals("Martin"))
                        .count()
        );
    }
}
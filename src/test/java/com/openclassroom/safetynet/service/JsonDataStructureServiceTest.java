package com.openclassroom.safetynet.service;

import com.openclassroom.safetynet.model.Person;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JsonDataStructureServiceTest {

    @Mock
    JsonDataStructureService jsonDataStructureService;

    static JsonDataStructure jsonDataStructureExp;

    @BeforeEach
    void init() {
        jsonDataStructureExp = JsonDataStructureExp.getJsonData();
    }


    @Test
    void testPersonNameFromJsonDataStructurePersonsList() {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);
        //je teste le nombre total de firestation que j'ai 4, ainsi que celui des personnes 6
        assertEquals(4,jsonDataStructureService.getJsonDataStructure().getFirestations().size());
        assertEquals(6,jsonDataStructureService.getJsonDataStructure().getPersons().size());
        //pour une personne qui s'appelle Carla on doit avoir l'addresse :14 rue Hoc
        for(Person x : jsonDataStructureExp.getPersons()) {
           if(x.getFirstName().equals("Carla")){
               x.getAddress().equals("14 rue Hoch");
           }
       }
    }
}

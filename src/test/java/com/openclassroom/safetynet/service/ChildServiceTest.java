package com.openclassroom.safetynet.service;

import com.openclassroom.safetynet.model.ChildInfoAndfamilyMembre;
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
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class ChildServiceTest {
    @Mock
    JsonDataStructureService jsonDataStructureService;

    @InjectMocks
    ChildService childService;
    static JsonDataStructure jsonDataStructureExp;

    @BeforeEach
    void init() {
        jsonDataStructureExp = JsonDataStructureExp.getJsonData();
    }

    @Test
    //Cette adresse contient un enfant et un autre membre de famille l'enfant s'appelle Rose et l'aute membre s'appelle Carla
    void getChildInfoAndfamilyMembreInAdress14rueHoch() throws IOException {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn( jsonDataStructureExp);
        ChildInfoAndfamilyMembre childInfoAndfamilyMembre1 = childService.getChildInfoAndfamilyMembre("14 rue Hoch");
        assertEquals(1,childInfoAndfamilyMembre1.getChildInfo().size());
        assertEquals(1,childInfoAndfamilyMembre1.getFamily().size());
        assertEquals("Carla",childInfoAndfamilyMembre1.getFamily().get(0).getFirstName());
        assertEquals("Rose",childInfoAndfamilyMembre1.getChildInfo().get(0).getFirstName());
    }

    @Test
    //Cette adresse ne contient pas d'enfant donc le resultat sera null
    void getChildInfoAndfamilyMembreInAdress50rueRivoli()  throws IOException {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn( jsonDataStructureExp);
        ChildInfoAndfamilyMembre childInfoAndfamilyMembre2 = childService.getChildInfoAndfamilyMembre("50 rue Rivoli");
        assertEquals(null,childInfoAndfamilyMembre2);
    }

}
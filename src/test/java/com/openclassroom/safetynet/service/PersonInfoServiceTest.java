package com.openclassroom.safetynet.service;

import com.openclassroom.safetynet.model.PersonInfo;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PersonInfoServiceTest {
@Mock
JsonDataStructureService jsonDataStructureService;

    @InjectMocks
    PersonInfoService  personInfoService;
    static JsonDataStructure jsonDataStructureExp;

    @BeforeEach
     void init() {
        jsonDataStructureExp = JsonDataStructureExp.getJsonData();
    }
    @Test
    void personInfoTestChrisMartin() {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);
        PersonInfo x = personInfoService.createPersonInfo("Chris","Martin");
        assertEquals("10 rue liberté",x.getAddress());
        assertFalse(x.getAge() ==5);

    }
    @Test
    void personInfoTestBobMarly() {
        //on teste si je donne le nom de Bob Marly, son age sera 61, et  présente d'allergie
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);
        PersonInfo y = personInfoService.createPersonInfo("Bob","Marly");
        assertEquals("Bob@gmail.com",y.getEmail());
        assertFalse(y.getAllergies().equals(Arrays.asList(new String[]{})));
        assertEquals(61, y.getAge());

    }
    @Test
    void personInfoTestJobCoo() {
        //on teste si je donne le nom de Job Coo, qui n'existe pas donc qui sera null
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);
        PersonInfo y = personInfoService.createPersonInfo("Job","Coo");
        //assertEquals(null,y.getEmail());
        assertEquals(null, y);

    }
}
package com.openclassroom.safetynet.service;

import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest

class PhoneNumberServiceTest {
    @Mock
    JsonDataStructureService jsonDataStructureService;

    @InjectMocks
    PhoneNumberService phoneNumberService;

    static JsonDataStructure jsonDataStructureExp;

    @BeforeAll
    static void init() {
        jsonDataStructureExp = JsonDataStructureExp.getJsonData();

    }
    @Test
    //pour la station 2 on obtient deux numéro du telephone dont le premier:"0202020"
    void getPhoneNumberTestFirestation2WichHad2Numbers() {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);
        List<String> phoneExp = phoneNumberService.getPersonsPhoneNumberByFirestation(2);
        assertEquals(2,phoneExp.size());
        assertTrue(phoneExp.get(0) == "0202020" );
        assertFalse(phoneExp.get(1) == "0202020" );
    }
}
package com.openclassroom.safetynet.service;

import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

class EmailServiceTest {
    @Mock
    JsonDataStructureService jsonDataStructureService;

    @InjectMocks
    EmailService emailService;
    static JsonDataStructure jsonDataStructureExp;

    @BeforeEach
    void init() {
        jsonDataStructureExp = JsonDataStructureExp.getJsonData();

    }
    @Test
    void emailsTestThatNumberOfMailInLilleIs6() {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn( jsonDataStructureExp);
        //méthode emails permet de récupérer la liste des emails pour une ville donnée exp : Lille
        //si je donne un emails qui n'existe pas c'est un assert False
        List<String> emailsExp = emailService.emails("Lille");
        assertEquals(6,emailsExp.size());
        assertTrue(emailsExp.get(0) == "Carla@gmail.com" );
        assertFalse(emailsExp.get(1) == "0202020" );
    }
    @Test
    void emailsTestThatNumberOfMailInNice() {
        //si je teste emails pour une ville non donnée exp Nice cela retourne 0
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn( jsonDataStructureExp);
        List<String> emailsExp = emailService.emails("Nice");
        assertEquals(0,emailsExp.size());
    }
}
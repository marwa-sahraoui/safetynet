package com.openclassroom.safetynet.service;

import com.openclassroom.safetynet.model.PersonWithMedicalRecord;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.websocket.Extension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PersonMedRecordServiceTest {

    @Mock
    JsonDataStructureService jsonDataStructureService;

    @InjectMocks
    PersonMedRecordService personMedRecordService;

    static JsonDataStructure jsonDataStructureExp;

    @BeforeAll
    static void init() {
        jsonDataStructureExp = JsonDataStructureExp.getJsonData();

    }

    @Test
    void getpersonMedRecordTestPersonsIn14Ruehoch() {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);
      //On verifie pour la liste de personnes qui habitent à l'adresse 14 rue Hoch; le prénom de la 2em personne  est Rose,
        //son age est 3ans, prénom de la premier personne Carla et son age est 31
        assertEquals(2,personMedRecordService.getpersonMedRecord("14 rue Hoch").size());
        List<PersonWithMedicalRecord> personWithMedicalRecord = personMedRecordService.getpersonMedRecord("14 rue Hoch");
        assertEquals("Rose",personWithMedicalRecord.get(1).getFirstName() );
        assertEquals(3,personWithMedicalRecord.get(1).getAge());
        assertEquals("Carla",personWithMedicalRecord.get(0).getFirstName());
        assertEquals(31,personWithMedicalRecord.get(0).getAge());

    }

    @Test
    void getpersonMedRecordTestPersonIn50RueRivoli() {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);
       ///On verifie pour la liste de personnes qui habitent à l'adresse 50 rue Rivoli;  qui s'appelle bob et pas Suzi
        // et ayant une allergie au pollen
        assertEquals(personMedRecordService.getpersonMedRecord("50 rue Rivoli").size(), 1);
        List<PersonWithMedicalRecord> personWithMedicalRecord = personMedRecordService.getpersonMedRecord("50 rue Rivoli");
        assertFalse(personWithMedicalRecord.get(0).getFirstName().equals("Suzi"));
        assertTrue(personWithMedicalRecord.get(0).getFirstName().equals("Bob"));
        assertEquals( Arrays.asList("pollen"),personWithMedicalRecord.get(0).getAllergies());

    }
}
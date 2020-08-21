package com.openclassroom.safetynet.service;

import com.openclassroom.safetynet.model.Firestation;
import com.openclassroom.safetynet.model.ResponsePersonWithStatistic;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FirestationServiceTest {
    @Mock
    JsonDataStructureService jsonDataStructureService;

    @InjectMocks
    FirestationService firestationService;
    static JsonDataStructure jsonDataStructureExp;

    @BeforeEach
    void init() {
        jsonDataStructureExp = JsonDataStructureExp.getJsonData();
    }

    @Test
    void getPersonsWithStatisticsTestStation1Assert1Adult2Child() throws IOException {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);
        //on teste pour la station 1 qU'on a un adulte et deux enfant
        ResponsePersonWithStatistic responseExp = firestationService.getPersonsWithStatistics(1);
        assertEquals(1, responseExp.getNumberAdult());
        assertEquals(2, responseExp.getNumberChild());
    }

    @Test
    void getPersonsWithStatisticsTestStationTestStationNotExisting4() throws IOException {
        //on teste pour la station 4 qui n'existe pas qu'on n'a ni enfants ni adultes
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);
        ResponsePersonWithStatistic responseExp = firestationService.getPersonsWithStatistics(4);
        assertEquals(0, responseExp.getNumberAdult());
        assertEquals(0, responseExp.getNumberChild());
        assertFalse(responseExp.getPerson().size() == 3);
    }


    @Test
    void addFirestationsAdd1firestationAndChecksizeIsIncreasing() {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);

        Firestation firestation = new Firestation();
        firestation.setStation("100");
        firestation.setAddress("New York");
       //si on ajoute une firestation on verifie que le nombre de firestation augmente de 4 à 5
        assertEquals(4, jsonDataStructureService.getJsonDataStructure().getFirestations().size());
        firestationService.addFirestation(firestation);
        assertEquals(5, jsonDataStructureService.getJsonDataStructure().getFirestations().size());
    }

    @Test
    void updateFirestation() {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);

        Firestation updatedFirestation = new Firestation();
        updatedFirestation.setStation("2");

        firestationService.updateFirestation(
                updatedFirestation, "14 rue Hoch"
        );
      //pour l'adresse: 14 rue Hoch on modifie le numero de station et on vérifie que l'adresse reste la même et que le nombre
        // de la station qui change de 1 à 2
        assertEquals("2", jsonDataStructureService.getJsonDataStructure().getFirestations().get(0).getStation());
        assertEquals("14 rue Hoch", jsonDataStructureService.getJsonDataStructure().getFirestations().get(0).getAddress());

        assertNotEquals(1, jsonDataStructureService.getJsonDataStructure().getFirestations().get(0).getStation());
        //pas logiq
    }

    @Test
    void deleteFirestation() {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);
        //on calcule le compte de firestation d'une adresse donnée :14 rue Hoch, avnt le delete 1 aprés devient 0
        assertEquals(1,
                jsonDataStructureService.getJsonDataStructure()
                        .getFirestations()
                        .stream()
                        .filter(firestation -> firestation.getAddress().equals("14 rue Hoch"))
                        .count()
        );
        firestationService.deleteFirestation("14 rue Hoch");

        assertEquals(0,
                jsonDataStructureService.getJsonDataStructure()
                        .getFirestations()
                        .stream()
                        .filter(firestation -> firestation.getAddress().equals("14 rue Hoch"))
                        .count()
        );
    }

    @Test
    void deleteFirestationWithWrongAddress() {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);
        // si je supprime une adresse que j 'ai pas je vérifie que le nombre initiale reste le même
        int initialSize = jsonDataStructureService.getJsonDataStructure().getFirestations().size();

        firestationService.deleteFirestation("Cité Safia 2");

        assertEquals(initialSize,
                jsonDataStructureService.getJsonDataStructure()
                        .getFirestations()
                        .size()
        );
    }
}
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
        ResponsePersonWithStatistic responseExp = firestationService.getPersonsWithStatistics(1);
        assertEquals(1, responseExp.getNumberAdult());
        assertEquals(2, responseExp.getNumberChild());
    }

    @Test
    void getPersonsWithStatisticsTestStationTestStationNotExisting4() throws IOException {
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

        assertEquals("2", jsonDataStructureService.getJsonDataStructure().getFirestations().get(0).getStation());
        assertEquals("14 rue Hoch", jsonDataStructureService.getJsonDataStructure().getFirestations().get(0).getAddress());

        assertNotEquals(1, jsonDataStructureService.getJsonDataStructure().getFirestations().get(0).getStation());
        //pas logiq
    }

    @Test
    void deleteFirestation() {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);

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

        int initialSize = jsonDataStructureService.getJsonDataStructure().getFirestations().size();

        firestationService.deleteFirestation("Cité Safia 2");

        assertEquals(initialSize,
                jsonDataStructureService.getJsonDataStructure()
                        .getFirestations()
                        .size()
        );
    }
}
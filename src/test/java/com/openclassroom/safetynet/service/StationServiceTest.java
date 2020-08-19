package com.openclassroom.safetynet.service;

import com.openclassroom.safetynet.model.Foyer;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest

class StationServiceTest {
    @Mock
    JsonDataStructureService jsonDataStructureService;

    @InjectMocks
    StationService stationService;

    static JsonDataStructure jsonDataStructureExp;
    @BeforeEach
    void init() {
        jsonDataStructureExp = JsonDataStructureExp.getJsonData();
    }

    @Test
    void getStationByAddressTestStationIn10RueLibertéIs2 () {
            when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);
            String station = stationService.getStationByAddress("10 rue liberté");
        assertEquals("2",jsonDataStructureService.getJsonDataStructure().getFirestations().get(1).getStation());
        assertNotEquals("5",jsonDataStructureService.getJsonDataStructure().getFirestations().get(3).getStation());
    }

    @Test
    void getFoyerListForStationsInList1And2WhichHave3Foyers() {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);
        List<Foyer> foyers = stationService.getFoyerListForStations(Arrays.asList("1", "2"));
        assertEquals(3,foyers.size());
        assertEquals("10 rue liberté",foyers.get(2).getAddress());
        assertEquals("14 rue Hoch",foyers.get(0).getAddress());
        assertEquals("1",foyers.get(0).getStation());
        assertNotEquals("10 Rue Jean Jacques Roussou",foyers.get(0).getAddress());
    }
}
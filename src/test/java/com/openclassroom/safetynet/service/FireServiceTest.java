package com.openclassroom.safetynet.service;

import com.openclassroom.safetynet.model.Fire;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class FireServiceTest {

    @Mock
    JsonDataStructureService jsonDataStructureService;

    @InjectMocks
    private PersonMedRecordService personMedRecordService;

    @InjectMocks
    private StationService stationService;

    static JsonDataStructure jsonDataStructureExp;

    @BeforeEach
    void init() {
        jsonDataStructureExp = JsonDataStructureExp.getJsonData();
    }

    @Test
    void fireTest50RueRivoli() {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);

        FireService fireService = new FireService(personMedRecordService, stationService);

        Fire fireExp = fireService.createFire("50 rue Rivoli");
        assertEquals("3", fireExp.getStation());
    }

    @Test
    void fireTest10RueJeanClaudeMary() {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);

        FireService fireService = new FireService(personMedRecordService, stationService);

        Fire fireExp = fireService.createFire("10 Rue Jean Claude Mary");
        assertNull(fireExp.getStation());
    }
    @Test
    void fireTest10RueVictorhugo() {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);

        FireService fireService = new FireService(personMedRecordService, stationService);

        Fire fireExp1 = fireService.createFire("10 rue Victor hugo");
        assertEquals("1", fireExp1.getStation());
    }
}
package com.openclassroom.safetynet.service;

import com.openclassroom.safetynet.model.ChildInfo;
import com.openclassroom.safetynet.model.Person;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void childrenPerAddressTestChildIn10RueLiberté() throws IOException {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);

        List<ChildInfo> children = childService.childrenPerAddress("10 rue liberté");

        assertEquals(1,children.size());
        assertThat(children.get(0).getFirstName()).isEqualTo("Chris");

    }
    @Test
    void childrenPerAddressTest14RueHoch() throws IOException {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);

        List<ChildInfo> children = childService.childrenPerAddress("14 rue Hoch");

        assertEquals(1,children.size());
    }
    @Test
    void childrenPerAddressTest01RueVAlbertAnchtein() throws IOException {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);

        List<ChildInfo> children = childService.childrenPerAddress("01RueVAlbertAnchtein");

        assertEquals(0,children.size());

    }
    @Test
    void childrenPerAddressTest50RueRivoli() throws IOException {
        when(jsonDataStructureService.getJsonDataStructure()).thenReturn(jsonDataStructureExp);

        List<ChildInfo> children = childService.childrenPerAddress("50 rue Rivoli");

        assertEquals(0,children.size());

    }
}
package com.openclassroom.safetynet.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import com.openclassroom.safetynet.utils.ReadJSon;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Logger;

@Service
public class JsonDataStructureService {

    private final JsonDataStructure jsonDataStructure;


    public JsonDataStructureService() throws IOException {
        String fileName = "json/data.json";

        ClassLoader classLoader = new ReadJSon().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        //File is found
       // logger.info("File Found : " + file.exists());

        // Read File Content
        String content = new String(Files.readAllBytes(file.toPath()));

        //
        ObjectMapper mapper = new ObjectMapper();
        jsonDataStructure = mapper.readValue(content, JsonDataStructure.class);
    }

    public JsonDataStructure getJsonDataStructure() {
        return jsonDataStructure;
    }
}

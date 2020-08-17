package com.openclassroom.safetynet.controller;


import com.openclassroom.safetynet.model.PersonInfo;
import com.openclassroom.safetynet.service.PersonInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class PersonInfoController {
 Logger logger = LoggerFactory.getLogger(PersonInfoController.class);

    @Autowired
    PersonInfoService personInfoService;

    @GetMapping("/personInfo")
    public PersonInfo personInfo(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) throws IOException {
        logger.info("request PersonInfo which his name is : " + firstName + lastName);
        PersonInfo personInfo = personInfoService.createPersonInfo(firstName, lastName);
        logger.info("personInfo which his name is: " + firstName + lastName + "is" + personInfo);
        return personInfo;
    }
}

package com.openclassroom.safetynet.controller;


import com.openclassroom.safetynet.model.PersonInfo;
import com.openclassroom.safetynet.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class PersonInfoController {

    @Autowired
    PersonInfoService personInfoService;

    @GetMapping("/personInfo")
    public PersonInfo personInfo(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) throws IOException {
        return personInfoService.personInfo(firstName,lastName);
   }
}

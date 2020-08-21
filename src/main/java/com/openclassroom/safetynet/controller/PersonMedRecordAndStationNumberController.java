package com.openclassroom.safetynet.controller;

import com.openclassroom.safetynet.model.PersonMedRecordAndStationNumber;
import com.openclassroom.safetynet.service.PersonMedRecordAndStationNumberService;
import com.openclassroom.safetynet.service.PersonMedRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class PersonMedRecordAndStationNumberController {
Logger logger = LoggerFactory.getLogger(PersonMedRecordAndStationNumberController.class);
    @Autowired
    PersonMedRecordService personMedRecordService;
    @Autowired
    PersonMedRecordAndStationNumberService personMedRecordAndStationNumberService;

    @GetMapping("/fire")
    public PersonMedRecordAndStationNumber createFire(@RequestParam("address") String address) throws IOException {
        logger.info("request fire:listPersonMedrecord +intStation living in address : " + address);
        PersonMedRecordAndStationNumber personMedRecordAndStationNumber = personMedRecordAndStationNumberService.createPersonMedRecordAndStationNumber(address);
        logger.info(" fire:listPersonMedrecord + intStation living in address : " + address + "is : " + personMedRecordAndStationNumber);
        return personMedRecordAndStationNumber;
    }
}

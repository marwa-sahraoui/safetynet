package com.openclassroom.safetynet.controller;


import com.openclassroom.safetynet.service.PhoneNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhoneAlertController {
Logger logger = LoggerFactory.getLogger(PhoneAlertController.class);
    @Autowired
    PhoneNumberService phoneNumberServic;

    @GetMapping("/phoneAlert")
    public List<String> getPhoneNumber(@RequestParam("firestation") Integer firestation) {
       logger.info("request list of phone which firstation number is " + firestation);
        List<String> phoneNumber = phoneNumberServic.getPersonsPhoneNumberByFirestation(firestation);
        logger.info("list of phone  is " + phoneNumber);
        return phoneNumber;
    }

}

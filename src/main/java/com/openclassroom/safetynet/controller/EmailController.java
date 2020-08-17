package com.openclassroom.safetynet.controller;


import com.openclassroom.safetynet.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmailController {
    Logger logger = LoggerFactory.getLogger(EmailController.class);
    @Autowired
    EmailService emailService;

    @GetMapping("/communityEmail")
    public List<String> getemails(@RequestParam("city") String city) {
        logger.info("request list emails living in city: " + city);
        List<String> emails = emailService.emails(city);
        logger.info("list emails living in city: " + city + ": " + emails);
        return emails;
    }
}

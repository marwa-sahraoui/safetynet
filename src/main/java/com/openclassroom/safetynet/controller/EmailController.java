package com.openclassroom.safetynet.controller;


import com.openclassroom.safetynet.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmailController {
    @Autowired
    EmailService emailService;

    @GetMapping("/communityEmail")
    public List<String> getemails(@RequestParam("city") String city){
        return emailService.emails(city);
    }
}

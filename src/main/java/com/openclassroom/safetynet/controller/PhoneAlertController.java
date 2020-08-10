package com.openclassroom.safetynet.controller;


import com.openclassroom.safetynet.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhoneAlertController {

    @Autowired
    PhoneNumberService phoneNumberServic;

    @GetMapping("/phoneAlert")
    public List<String> getPhoneNumber(@RequestParam("firestation") Integer firestation) {
        return phoneNumberServic.getPhoneNumber(firestation);
    }

}

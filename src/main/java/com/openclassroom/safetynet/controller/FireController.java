package com.openclassroom.safetynet.controller;

import com.openclassroom.safetynet.model.Fire;
import com.openclassroom.safetynet.service.FireService;
import com.openclassroom.safetynet.service.PersonMedRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class FireController {

    @Autowired
    PersonMedRecordService personMedRecordService;
    @Autowired
    FireService fireService;

    @GetMapping("/fire")
    public Fire fire(@RequestParam("address") String address) throws IOException {
        return fireService.fire(address);
    }
}

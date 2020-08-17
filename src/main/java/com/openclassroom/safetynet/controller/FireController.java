package com.openclassroom.safetynet.controller;

import com.openclassroom.safetynet.model.Fire;
import com.openclassroom.safetynet.service.FireService;
import com.openclassroom.safetynet.service.PersonMedRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class FireController {
Logger logger = LoggerFactory.getLogger(FireController.class);
    @Autowired
    PersonMedRecordService personMedRecordService;
    @Autowired
    FireService fireService;

    @GetMapping("/fire")
    public Fire createFire(@RequestParam("address") String address) throws IOException {
        logger.info("request fire:listPersonMedrecord +intStation living in address : " + address);
        Fire fire = fireService.createFire(address);
        logger.info(" fire:listPersonMedrecord + intStation living in address : " + address + "is : " + fire);
        return fire;
    }
}

package com.openclassroom.safetynet.
        controller;


import com.openclassroom.safetynet.model.ChildInfo;
import com.openclassroom.safetynet.model.Person;
import com.openclassroom.safetynet.service.ChildService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
public class ChildController {
 Logger logger = LoggerFactory.getLogger(ChildController.class);
    @Autowired
    ChildService childService;

    @GetMapping("/childAlert")
    public List<ChildInfo> children(@RequestParam("address") String address) throws IOException {
        logger.info("request list childen living in address: " + address);
        List<ChildInfo> children = childService.childrenPerAddress(address);
        logger.info("list childen living in address: " + address + ": " + children);
        return children;
    }

}





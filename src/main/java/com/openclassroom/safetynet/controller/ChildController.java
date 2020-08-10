package com.openclassroom.safetynet.
        controller;


import com.openclassroom.safetynet.model.Person;
import com.openclassroom.safetynet.service.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
public class ChildController {

    @Autowired
    ChildService childService;

    @GetMapping("/childAlert")
    public List<Person> children(@RequestParam("address") String address) throws IOException {
        return childService.childrenPerAddress(address);
    }

}





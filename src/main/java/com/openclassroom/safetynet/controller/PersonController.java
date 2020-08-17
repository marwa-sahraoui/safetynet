package com.openclassroom.safetynet.controller;


import com.openclassroom.safetynet.model.Person;
import com.openclassroom.safetynet.service.PersonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PersonController {

    Logger logger = LoggerFactory.getLogger(PersonController.class);

 @Autowired
private PersonService personService;
 
    @GetMapping ("/persons")
    public List<Person> listPerson() {
        logger.info("request list of Persons");
        List<Person> persons = personService.listPerson();
        logger.info("list of persons = " + persons);
        return persons;

    }
    
    @PostMapping("/person")
    public void addPerson(@RequestBody Person person) {
        logger.info("request add new person");
        personService.addPerson(person);
    }

    @PutMapping("/person/{firstName}/{lastName}")
    public void updatePerson(@RequestBody Person person, @PathVariable String firstName, @PathVariable String lastName ) {
        logger.info("updating person with name = " + firstName + lastName + "person become : " + person);
        personService.updatePerson(person,firstName, lastName);
    }

    @DeleteMapping ("/person/{firstName}/{lastName}")
    public void deletePerson(@PathVariable String firstName, @PathVariable String lastName) {
        logger.info("updating person with name = " + firstName + lastName);
                personService.deletePerson(firstName, lastName);
    }
}

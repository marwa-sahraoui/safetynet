package com.openclassroom.safetynet.controller;


import com.openclassroom.safetynet.model.Person;
import com.openclassroom.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

 @Autowired
private PersonService personService;
 
    @GetMapping ("/persons")
    public List<Person> listPerson() {
        return personService.listPerson();
    }
    
    @PostMapping("/person")
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @PutMapping("/person/{firstName}/{lastName}")
    public void updatePerson(@RequestBody Person person, @PathVariable String firstName, @PathVariable String lastName ) {
        personService.updatePerson(person,firstName, lastName);
    }

    @DeleteMapping ("/person/{firstName}/{lastName}")
    public void deletePerson(@RequestBody Person person, @PathVariable String firstName, @PathVariable String lastName ) {
        personService.deletePerson(person,firstName, lastName);
    }
}

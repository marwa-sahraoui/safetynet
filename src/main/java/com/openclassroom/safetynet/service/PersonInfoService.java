package com.openclassroom.safetynet.service;


import com.openclassroom.safetynet.model.Medicalrecord;
import com.openclassroom.safetynet.model.Person;
import com.openclassroom.safetynet.model.PersonInfo;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static java.time.temporal.ChronoUnit.YEARS;

@Service
 public class PersonInfoService {
    @Autowired
    private JsonDataStructureService jsonDataStructureService;
//javadoc + class de test
    public PersonInfo createPersonInfo(String firstName, String lastName) {

        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();

        for (Medicalrecord m : jsonDataStructure.getMedicalrecords()) {
            for (Person person : jsonDataStructure.getPersons()) {
                if (person.getFirstName().equals(m.getFirstName()) && person.getLastName().equals(m.getLastName())
                        && (person.getFirstName()).equals(firstName) && (person.getLastName().equals(lastName))) {

                    String date = m.getBirthdate();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate dateTime = LocalDate.parse(date, formatter);


                    YEARS.between(dateTime, LocalDate.now());
                    int age = (int) YEARS.between(dateTime, LocalDate.now());
                    m.getMedications();
                    m.getAllergies();

                    PersonInfo personInfo = new PersonInfo(person.getFirstName(), person.getLastName(), person.getAddress()
                            , age, person.getEmail(), m.getMedications(), m.getAllergies());
                    System.out.println(personInfo);
                    return personInfo;
                }

            }

        }
        return null;

    }
}

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
    /**
     *
     * @param firstName
     * @param lastName
     * @return personInfo Nom/prenom/age/adresse/ medications/allergies  à partir du nom et prénom donné
     */
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
                    //méthode pour récupérer l'age à partir de la date de birthday
                    int age = (int) YEARS.between(dateTime, LocalDate.now());

                    PersonInfo personInfo = new PersonInfo(person.getFirstName(), person.getLastName(), person.getAddress()
                            , age, person.getEmail(), m.getMedications(), m.getAllergies());

                    return personInfo;
                }

            }

        }
        return null;

    }
}

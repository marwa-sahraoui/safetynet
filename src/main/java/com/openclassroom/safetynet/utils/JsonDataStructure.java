

package com.openclassroom.safetynet.utils;

import com.openclassroom.safetynet.model.*;

import java.util.List;

public class JsonDataStructure {

    private List<Person> persons;
    private List<Medicalrecord> medicalrecords;
    private List<Firestation> firestations;

    //    public List<PersonWithMedicalRecord> getPersonWithMedicalRecords() {
//        return personWithMedicalRecords;
//    }
//
//    public void setPersonWithMedicalRecords(List<PersonWithMedicalRecord> personWithMedicalRecords) {
//        this.personWithMedicalRecords = personWithMedicalRecords;
//    }
//
//    private List<PersonWithMedicalRecord> personWithMedicalRecords;

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Medicalrecord> getMedicalrecords() {
        return medicalrecords;
    }

    public void setMedicalrecords(List<Medicalrecord> medicalrecords) {
        this.medicalrecords = medicalrecords;
    }

    public List<Firestation> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<Firestation> firestations) {
        this.firestations = firestations;
    }
}
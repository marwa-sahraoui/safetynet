package com.openclassroom.safetynet.model;

import java.util.List;

public class Foyer {

    private String station;
     private String address;
     private List<PersonWithMedicalRecord> personWithMedicalRecords;

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PersonWithMedicalRecord> getPersonWithMedicalRecords() {
        return personWithMedicalRecords;
    }

    public void setPersonWithMedicalRecord(List<PersonWithMedicalRecord> personWithMedicalRecords) {
        this.personWithMedicalRecords = personWithMedicalRecords;
    }

    public Foyer(String station, String address, List<PersonWithMedicalRecord> personWithMedicalRecords) {
        this.station = station;
        this.address = address;
        this.personWithMedicalRecords = personWithMedicalRecords;
    }

    @Override
    public String toString() {
        return "Foyer{" +
                "station='" + station + '\'' +
                ", address='" + address + '\'' +
                ", personWithMedicalRecords=" + personWithMedicalRecords +
                '}';
    }
}

package com.openclassroom.safetynet.model;

import java.util.List;

public class PersonMedRecordAndStationNumber {

    private List<PersonWithMedicalRecord> personWithMedicalRecord;
    private String station;

    public PersonMedRecordAndStationNumber(List<PersonWithMedicalRecord> personWithMedicalRecord, String station) {
        this.personWithMedicalRecord = personWithMedicalRecord;
        this.station = station;
    }

    public List<PersonWithMedicalRecord> getPersonWithMedicalRecord() {
        return personWithMedicalRecord;
    }

    public void setPersonWithMedicalRecord(List<PersonWithMedicalRecord> personWithMedicalRecord) {
        this.personWithMedicalRecord = personWithMedicalRecord;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "PersonMedRecordAndStationNumber {" +
                "personWithMedicalRecord=" + personWithMedicalRecord +
                ", station='" + station + '\'' +
                '}';
    }
}

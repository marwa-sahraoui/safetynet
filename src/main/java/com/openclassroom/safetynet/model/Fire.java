package com.openclassroom.safetynet.model;

import java.util.List;

public class Fire {
    private List<PersonWithMedicalRecord> personWithMedicalRecord;
    private String station;

    public Fire(List<PersonWithMedicalRecord> personWithMedicalRecord, String station) {
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
        return "Fire{" +
                "personWithMedicalRecord=" + personWithMedicalRecord +
                ", station='" + station + '\'' +
                '}';
    }
}

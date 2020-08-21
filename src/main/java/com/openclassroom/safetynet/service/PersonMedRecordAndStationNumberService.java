package com.openclassroom.safetynet.service;


import com.openclassroom.safetynet.model.PersonMedRecordAndStationNumber;
import com.openclassroom.safetynet.model.PersonWithMedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonMedRecordAndStationNumberService {

    private PersonMedRecordService personMedRecordService;
    private StationService stationService;

    @Autowired
    public PersonMedRecordAndStationNumberService(PersonMedRecordService personMedRecordService, StationService stationService) {
        this.personMedRecordService = personMedRecordService;
        this.stationService = stationService;
    }
    /**
     *
     * @param address
     * @return lobjet personWithMedRecordAndStationNumber : à la fois num de station ainsi que la liste des personnes
     * //avec leur infos medicals
     */

    public PersonMedRecordAndStationNumber createPersonMedRecordAndStationNumber(String address) {

        List<PersonWithMedicalRecord> total = personMedRecordService.getpersonMedRecord(address);

        String stats = stationService.getStationByAddress(address);

        return new PersonMedRecordAndStationNumber(total, stats);
    }
}

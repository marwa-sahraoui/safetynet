package com.openclassroom.safetynet.service;


import com.openclassroom.safetynet.model.Fire;
import com.openclassroom.safetynet.model.PersonWithMedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireService {

    private PersonMedRecordService personMedRecordService;

    private StationService stationService;

    @Autowired
    public FireService(PersonMedRecordService personMedRecordService, StationService stationService) {
        this.personMedRecordService = personMedRecordService;
        this.stationService = stationService;
    }

    public Fire createFire(String address) {

        List<PersonWithMedicalRecord> total = personMedRecordService.getpersonMedRecord(address);

        String stats = stationService.getStationByAddress(address);

        return new Fire(total, stats);
    }
}

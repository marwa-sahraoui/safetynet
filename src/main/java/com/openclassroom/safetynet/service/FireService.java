package com.openclassroom.safetynet.service;


import com.openclassroom.safetynet.model.Fire;
import com.openclassroom.safetynet.model.PersonWithMedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class FireService {
    @Autowired
    private  PersonMedRecordService personMedRecordService;
    @Autowired
    private StationService stationService;

    public Fire fire (String address) throws IOException {

        List<PersonWithMedicalRecord> total =  personMedRecordService.getpersonMedRecord(address);

        String x = stationService.stats(address);

        Fire fire = new Fire(total, x);
        return fire;

    }
}

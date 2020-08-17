package com.openclassroom.safetynet.controller;


import com.openclassroom.safetynet.model.Medicalrecord;
import com.openclassroom.safetynet.service.MedicalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalRecordController {

    Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);
    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("/medicalRecords")

    public List<Medicalrecord> listMedRecord() {
        logger.info("request list of medicalRecord");
        List<Medicalrecord> medicalRecords =medicalRecordService.listMedRecord();
        logger.info("list of medicalRecord is " + medicalRecords);
        return medicalRecords;
    }

    @PostMapping("/medicalRecord")
    public void addMedicalRecord(@RequestBody Medicalrecord medicalrecord) {
        logger.info("adding new medicalrecord");
        medicalRecordService.addMedicalRecord(medicalrecord);
    }

    @PutMapping("/medicalRecord/{firstName}/{lastName}")
    public void updateMedicalRecord(@RequestBody Medicalrecord medicalrecord, @PathVariable String firstName, @PathVariable String lastName) {
        logger.info("updating medicalrecord which had name : " + firstName + lastName);
        medicalRecordService.updateMedicalRecord(medicalrecord, firstName, lastName);
    }

    @DeleteMapping("/medicalRecord/{firstName}/{lastName}")
    public void deleteMedicalRecord(@PathVariable String firstName, @PathVariable String lastName) {
        logger.info("deleting medicalrecord which had name : " + firstName + lastName);
        medicalRecordService.deleteMedicalRecord(firstName, lastName);
    }
}

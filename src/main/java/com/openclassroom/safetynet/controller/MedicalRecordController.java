package com.openclassroom.safetynet.controller;


import com.openclassroom.safetynet.model.Medicalrecord;
import com.openclassroom.safetynet.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("/medicalRecords")
    public List<Medicalrecord> listMedRecord() {
       return medicalRecordService.listMedRecord();
    }
    @PostMapping("/medicalRecord")
    public void addMedicalRecord(@RequestBody Medicalrecord medicalrecord){
        medicalRecordService.addMedicalRecord(medicalrecord);
    }
    @PutMapping("/medicalRecord/{firstName}/{lastName}")
    public void updateMedicalRecord(@RequestBody Medicalrecord medicalrecord, @PathVariable String firstName, @PathVariable String lastName){
        medicalRecordService.updateMedicalRecord(medicalrecord,firstName,lastName);
    }
    @DeleteMapping("/medicalRecord/{firstName}/{lastName}")
    public void deleteMedicalRecord(@RequestBody Medicalrecord medicalrecord, @PathVariable String firstName, @PathVariable String lastName) {
        medicalRecordService.deleteMedicalRecord(medicalrecord,firstName,lastName);
    }
}

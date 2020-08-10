package com.openclassroom.safetynet.service;


import com.openclassroom.safetynet.model.Medicalrecord;
import com.openclassroom.safetynet.model.Person;
import com.openclassroom.safetynet.utils.JsonDataStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {
    @Autowired
    private JsonDataStructureService jsonDataStructureService;

    public List<Medicalrecord> listMedRecord() {
        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();
        return jsonDataStructure.getMedicalrecords();
    }

    public void addMedicalRecord(Medicalrecord medicalrecord) {
        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();
        jsonDataStructure.getMedicalrecords().add(medicalrecord);
    }

    public void updateMedicalRecord(Medicalrecord medicalrecord, String firstName, String lastName) {
        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();
        for(int i=0; i< jsonDataStructure.getMedicalrecords().size(); i++){
            Medicalrecord m = jsonDataStructure.getMedicalrecords().get(i);
            if(m.getFirstName().equals(firstName)&&(m.getLastName().equals(lastName))){
                jsonDataStructure.getMedicalrecords().set(i,medicalrecord);
                return;
            }
        }
    }


    public void deleteMedicalRecord(Medicalrecord medicalrecord, String firstName, String lastName) {
        JsonDataStructure jsonDataStructure = jsonDataStructureService.getJsonDataStructure();
        jsonDataStructure.getMedicalrecords().removeIf(m->m.getFirstName().equals(firstName) && m.getLastName().equals(lastName));
    }
}

package com.medicalhistorysampling.demo.service;

import com.medicalhistorysampling.demo.model.MedicalHistory;
import com.medicalhistorysampling.demo.repository.MedicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalHistoryService {
    private MedicalHistoryRepository medicalHistoryRepository;
    private PatientService patientService;

    @Autowired
    public MedicalHistoryService(MedicalHistoryRepository medicalHistoryRepository, PatientService patientService){
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.patientService = patientService;
    }

    public List<MedicalHistory> getAllMedicalHistory(){
        return medicalHistoryRepository.findAll();
    }

    public MedicalHistory createMedicalHistory(MedicalHistory medicalHistory){
        if(medicalHistory.getPatient().getId()!=0){
            medicalHistory.setPatient(patientService.findPatientById(medicalHistory.getPatient().getId()));
        }
        return medicalHistoryRepository.save(medicalHistory);
    }
}

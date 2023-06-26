package com.medicalhistorysampling.demo.service;

import com.medicalhistorysampling.demo.model.Patient;
import com.medicalhistorysampling.demo.repository.PatientRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }


    public Patient createPatient(Patient patient){
        return patientRepository.save(patient);
    }

    public Patient findPatientById(int id){
        Patient patient = patientRepository.findById(id).orElseThrow(()->new RuntimeException("User does not exist"));
        Hibernate.initialize(patient.getMedicalHistoryList());
        return patient;
    }
}

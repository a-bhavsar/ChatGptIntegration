package com.medicalhistorysampling.demo.controller;

import com.medicalhistorysampling.demo.model.Patient;
import com.medicalhistorysampling.demo.service.PatientService;
import org.hibernate.Hibernate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService){
        this.patientService = patientService;
    }

    @PostMapping(value = "")
    public Patient createPatient(@RequestBody Patient patient){
        return patientService.createPatient(patient);
    }

    @GetMapping(value = "/{id}")
    public Patient findPatientById(@PathVariable int id){
        return patientService.findPatientById(id);
    }
}

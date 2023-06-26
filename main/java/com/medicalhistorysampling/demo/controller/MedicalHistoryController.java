package com.medicalhistorysampling.demo.controller;
import com.medicalhistorysampling.demo.model.MedicalHistory;
import com.medicalhistorysampling.demo.service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "medicalHistory")
public class MedicalHistoryController {

    private MedicalHistoryService medicalHistoryService;

    @Autowired
    public MedicalHistoryController(MedicalHistoryService medicalHistoryService){
        this.medicalHistoryService = medicalHistoryService;

    }

    @GetMapping(value = "/")
    public List<MedicalHistory> getAllMedicalHistory(){
        return medicalHistoryService.getAllMedicalHistory();
    }

    @PostMapping(value = "/")
    public MedicalHistory createMedicalHistory(@RequestBody MedicalHistory medicalHistory){
        return medicalHistoryService.createMedicalHistory(medicalHistory);
    }
}

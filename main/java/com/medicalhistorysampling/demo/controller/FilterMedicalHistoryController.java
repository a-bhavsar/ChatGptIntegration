package com.medicalhistorysampling.demo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medicalhistorysampling.demo.model.MedicalAppointment;
import com.medicalhistorysampling.demo.model.MessageRequest;
import com.medicalhistorysampling.demo.service.FilterMedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/medicalForm")
public class FilterMedicalHistoryController {

    private FilterMedicalHistoryService filterMedicalHistoryService;

    @Autowired
    public FilterMedicalHistoryController(FilterMedicalHistoryService filterMedicalHistoryService){
        this.filterMedicalHistoryService = filterMedicalHistoryService;

    }

    @PostMapping(value = "/filter")
    public MedicalAppointment filterMedicalHistory(@RequestBody MessageRequest messageRequest) throws IOException {
        return filterMedicalHistoryService.filterMedicalHistory(messageRequest);
    }
}

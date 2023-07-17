package com.medicalhistorysampling.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medicalhistorysampling.demo.model.MedicalAppointment;
import com.medicalhistorysampling.demo.model.MessageRequest;
import com.medicalhistorysampling.demo.repository.MedicalAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class FilterMedicalHistoryService {

    private ChatGptService chatGptService;
    private MedicalAppointmentRepository medicalAppointmentRepository;

    @Autowired
    public FilterMedicalHistoryService(ChatGptService chatGptService, MedicalAppointmentRepository medicalAppointmentRepository){
        this.chatGptService = chatGptService;
        this.medicalAppointmentRepository = medicalAppointmentRepository;
    }

    public String[] weekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thurday", "Friday", "Saturday"};

    public Date today = new Date();
    public SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    public String formattedDate = format.format(today.getTime());

    public MedicalAppointment filterMedicalHistory(MessageRequest messageRequest) throws IOException {
        String messageSummary = messageRequest.getMessage();
        String message = "Make a JSON Response Data from the following text given to you and make sure to add only following keys : firstName, lastName, age, gender, bloodGroup, symptoms, prescription, disease, department, nextSchedule, recommendedFood, restrictedFood, bloodPressure and diabetesLevel.Consider guy as MALE and girl as FEMALE. Age should be of integer type, nextSchedule should be of Date, bloodPressure should be of double type, diabetesLevel should be of double type. Keep the entries as empty string if it does not match the criteria Consider today's date as " + formattedDate +  ". Consider today's day as " + weekDays[Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1] + ".Keep the nextSchedule as YYYY-MM-DD type. Also consider blood Group as a 2-letter word such as B+ and if the conversation doesnt specify it expicitly consider it to be positive. Also, ensure that department should be the actual name of a department and not a doctor's position. Please ensure that correct nextSchedule is returned. If the next appointment is occuring before the current date, increment the year.  Return me JSON Data only and no extra information. Message Summary : " + messageSummary;
        System.out.println(message);
        MessageRequest messageRequestInput = new MessageRequest(message);
        String response = chatGptService.getChatResponse(messageRequestInput);
        ObjectMapper objectMapper = new ObjectMapper();
        MedicalAppointment medicalAppointment = objectMapper.readValue(response, MedicalAppointment.class);
        return medicalAppointmentRepository.save(medicalAppointment);
    }
}

package com.medicalhistorysampling.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medicalhistorysampling.demo.model.MessageRequest;
import com.medicalhistorysampling.demo.model.Patient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Service
public class ChatGptService {

    private static final String API_ENDPOINT = "https://api.openai.com/v1/chat/completions";
    @Value("${api.key}")
    private String API_KEY;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private PatientService patientService;

    public ChatGptService(PatientService patientService){
        this.patientService = patientService;
    }


    public String getChatResponse(@RequestBody MessageRequest messageRequest) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost request = new HttpPost(API_ENDPOINT);
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + API_KEY);
        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        String payload = "{\"model\":\"gpt-3.5-turbo\",\"messages\":[{\"role\":\"system\",\"content\":\"You are\"},{\"role\":\"user\",\"content\":\"" + messageRequest.getMessage() + "\"}]}";
        request.setEntity(new StringEntity(payload));



        CloseableHttpResponse response = httpClient.execute(request);

        JsonNode responseNode = objectMapper.readTree(response.getEntity().getContent());
        String responseBody = responseNode.get("choices").get(0).get("message").get("content").asText();

        httpClient.close();
        response.close();

        return responseBody;
    }

    public String getSummaryFromMedicalHistory(int id) throws IOException {
        Patient patient = patientService.findPatientById(id);
        String message = "Give me 5-6 sentenced summary for the following object which should cover all the aspects mentioned such that I may not see the data provided and still know everything.This data is of a single patient's medical history. Emphasize more on disease, prescription and symptoms. Also, give the output such that you are responding to a doctor" + patient.getMedicalHistoryList();
        MessageRequest messageRequest = new MessageRequest(message);
        return getChatResponse(messageRequest);
    }
}

package com.medicalhistorysampling.demo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medicalhistorysampling.demo.model.MessageRequest;
import com.medicalhistorysampling.demo.service.ChatGptService;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping(value = "/ask")
@RestController
public class ChatGptClient {

    private ChatGptService chatGptService;

    public ChatGptClient(ChatGptService chatGptService){
        this.chatGptService = chatGptService;
    }




    @PostMapping(value="")
    public String getChatResponse(@RequestBody MessageRequest messageRequest) throws IOException {
        return chatGptService.getChatResponse(messageRequest);
    }

    @PostMapping(value="{id}")
    public String getSummaryFromMedicalHistory(@PathVariable int id) throws IOException {
        return chatGptService.getSummaryFromMedicalHistory(id);
    }

}

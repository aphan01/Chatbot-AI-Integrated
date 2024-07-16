package com.example.cbot.beta2.controller;

import com.example.cbot.beta2.model.ChatResponse;
import com.example.cbot.beta2.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping("/message")
    public ChatResponse handleMessage(@RequestBody Map<String, String> payload) {
        String message = payload.get("message");
        System.out.println("Received message: " + message);
        ChatResponse response = chatbotService.handleMessage(message);
        System.out.println("Sending response: " + response);
        return response;
    }
}

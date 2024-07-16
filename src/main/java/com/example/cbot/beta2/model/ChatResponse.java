package com.example.cbot.beta2.model;

import java.util.List;
import java.util.Map;

public class ChatResponse {
    private String response;
    private List<Map<String, String>> buttons;

    // Constructor, getters, and setters

    public ChatResponse(String response, List<Map<String, String>> buttons) {
        this.response = response;
        this.buttons = buttons;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<Map<String, String>> getButtons() {
        return buttons;
    }

    public void setButtons(List<Map<String, String>> buttons) {
        this.buttons = buttons;
    }

    @Override
    public String toString() {
        return "ChatResponse{" +
                "response='" + response + '\'' +
                ", buttons=" + buttons +
                '}';
    }
}

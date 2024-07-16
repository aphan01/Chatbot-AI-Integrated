package com.example.cbot.beta2.service;

import com.example.cbot.beta2.model.ChatMessage;
import com.example.cbot.beta2.model.ChatResponse;
import com.example.cbot.beta2.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ChatbotService {

    private static final Logger logger = LoggerFactory.getLogger(ChatbotService.class);

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private KeywordService keywordService;

    private static final Map<String, String> defaultResponses = new HashMap<>();

    static {
        defaultResponses.put("hello", "Hi there! How can I help you today?");
        defaultResponses.put("hi", "Hello! How can I assist you?");
        defaultResponses.put("thank you", "You're welcome! Is there anything else I can help with?");
        defaultResponses.put("thanks", "You're welcome! Is there anything else I can help with?");
        defaultResponses.put("bye", "Goodbye! Have a great day!");
    }

    public ChatResponse handleMessage(String message) {
        message = message.toLowerCase().trim();
        logger.info("Received message: '{}'", message);

        // Check for button options
        Map<String, String> buttonOptions = keywordService.getButtonOptionsForKeyword(message);
        if (buttonOptions != null && !buttonOptions.isEmpty()) {
            logger.info("Found button options for keyword '{}': {}", message, buttonOptions);
            saveChatMessage(message, "Here are some options:");
            List<Map<String, String>> buttons = new ArrayList<>();
            for (Map.Entry<String, String> entry : buttonOptions.entrySet()) {
                buttons.add(Map.of("text", entry.getKey(), "url", entry.getValue()));
            }
            logger.info("Sending button options: {}", buttons);
            return new ChatResponse("Here are some options:", buttons);
        } else {
            logger.info("No button options found for keyword '{}'", message);
        }

        // Check for links
        String keyword = extractKeyword(message);
        logger.info("Extracted keyword: '{}'", keyword);
        String link = keywordService.getLinkForKeyword(keyword);
        if (link != null) {
            logger.info("Found link for keyword '{}': {}", keyword, link);
            saveChatMessage(message, "Here is the link you might find useful: " + link);
            List<Map<String, String>> links = new ArrayList<>();
            links.add(Map.of("text", keyword, "url", link));
            logger.info("Sending link: {}", links);
            return new ChatResponse("Here is the link you might find useful:", links);
        } else {
            logger.info("No link found for keyword '{}'", keyword);
        }

        // Default response
        String response = getDefaultResponse(message);
        logger.info("Sending response: '{}'", response);
        saveChatMessage(message, response);
        return new ChatResponse(response, new ArrayList<>());
    }

    private String extractKeyword(String message) {
        for (String keyword : keywordService.getKeywords()) {
            if (message.contains(keyword.toLowerCase())) {
                logger.info("Message '{}' contains keyword '{}'", message, keyword);
                return keyword;
            }
        }
        return message;
    }

    private void saveChatMessage(String message, String response) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessage(message);
        chatMessage.setResponse(response);
        chatMessageRepository.save(chatMessage);
    }

    private String getDefaultResponse(String message) {
        for (Map.Entry<String, String> entry : defaultResponses.entrySet()) {
            if (message.contains(entry.getKey())) {
                logger.info("Found default response for keyword '{}': {}", entry.getKey(), entry.getValue());
                return entry.getValue();
            }
        }
        logger.info("No default response found for message '{}'", message);
        return "I'm here to assist you. Please enter a keyword or choose an option.";
    }
}

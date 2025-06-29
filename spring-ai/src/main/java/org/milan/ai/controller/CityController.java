package org.milan.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Milan Rathod
 */
@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final ChatClient chatClient;

    public CityController(OpenAiChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping
    public ResponseEntity<String> cityFaq(@RequestParam(value = "message") String message) {
        var system = new SystemMessage("You are a helpful AI Assistant answering questions about cities around the world.");
        var user = new UserMessage(message);

        OpenAiChatOptions options = OpenAiChatOptions.builder()
            .function("currentWeatherFunction")
            .build();

        Prompt prompt = new Prompt(system, user);

        ChatResponse chatResponse = chatClient
            .prompt(prompt)
            .options(options)
            .call()
            .chatResponse();

        String response = chatResponse.getResult().getOutput().getText();

        return ResponseEntity.ok(response);
    }
}

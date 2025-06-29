package org.milan.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Milan Rathod
 */
@RestController
@RequestMapping("/api/chat-memory/")
public class ChatMemoryController {

    private final ChatClient chatClient;

    public ChatMemoryController(OpenAiChatModel chatModel) {
        this.chatClient = ChatClient.
            create(chatModel)
            .mutate()
            .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory())).build();
    }

    @GetMapping("/{message}")
    public ResponseEntity<String> getAnswer(@PathVariable("message") String message) {

        ChatResponse chatResponse = chatClient
            .prompt()
            .user(message)
            .call()
            .chatResponse();

        String response = chatResponse.getResult().getOutput().getText();

        return ResponseEntity.ok(response);
    }
}

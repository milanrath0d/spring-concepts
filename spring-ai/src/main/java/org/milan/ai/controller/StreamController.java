package org.milan.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author Milan Rathod
 */
@RestController
@RequestMapping("/api/streams")
public class StreamController {

    private final ChatClient chatClient;

    public StreamController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/without")
    public String withoutStream(@RequestParam(value = "message",
        defaultValue = "I'm visiting Bengaluru next month, what are 10 places I must visit?") String message) {
        return chatClient.prompt()
            .user(message)
            .call()
            .content();
    }

    @GetMapping
    public Flux<String> stream(@RequestParam(value = "message",
        defaultValue = "I'm visiting Bengaluru next month, what are 10 places I must visit?") String message) {
        return chatClient.prompt()
            .user(message)
            .stream()
            .content();
    }
}

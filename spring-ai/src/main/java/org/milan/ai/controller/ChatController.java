package org.milan.ai.controller;

import org.milan.ai.model.Author;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Milan Rathod
 */
@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/artist.st")
    private Resource artistPrompt;

    @Value("classpath:/prompts/latest-events.st")
    private Resource latestEventsPrompt;

    @Value("classpath:/docs/latest-events.txt")
    private Resource latestEventsContextPrompt;

    public ChatController(OpenAiChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("/{message}")
    public ResponseEntity<String> getAnswer(@PathVariable("message") String message) {
        var system = new SystemMessage("Your primary function is to answer questions. Don't be answer political questions. If someone ask you a question about politics, you should say something like: \"I don't know, I'm not a politician.");
        var user = new UserMessage(message);

        Prompt prompt = new Prompt(system, user);

        ChatResponse chatResponse = chatClient
            .prompt(prompt)
            .call()
            .chatResponse();

        System.out.println(chatResponse.getMetadata().getModel());

        String response = chatResponse.getResult().getOutput().getText();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/prompt/songs")
    public ResponseEntity<List<String>> findTopSongsByArtists(@RequestParam(value = "artist") String artist) {
        PromptTemplate promptTemplate = new PromptTemplate(artistPrompt);

        ListOutputConverter listOutputConverter = new ListOutputConverter(new DefaultConversionService());

        Prompt prompt = promptTemplate.create(Map.of("artist", artist, "format", listOutputConverter.getFormat()));

        ChatResponse chatResponse = chatClient
            .prompt(prompt)
            .call()
            .chatResponse();

        System.out.println(chatResponse.getMetadata().getModel());

        String response = chatResponse.getResult().getOutput().getText();

        return ResponseEntity.ok(listOutputConverter.convert(response));
    }

    @GetMapping("/prompt/authors/social-links")
    public ResponseEntity<Map<String, Object>> findAuthorSocialLinks(@RequestParam(value = "author") String author) {
        String authorPrompt = "Generate a list of links for the author {author}. Include the authors name as the key and any social network links as json object {format}";

        PromptTemplate promptTemplate = new PromptTemplate(authorPrompt);

        MapOutputConverter mapOutputConverter = new MapOutputConverter();

        Prompt prompt = promptTemplate.create(Map.of("author", author, "format", mapOutputConverter.getFormat()));

        ChatResponse chatResponse = chatClient
            .prompt(prompt)
            .call()
            .chatResponse();

        System.out.println(chatResponse.getMetadata().getModel());

        String response = chatResponse.getResult().getOutput().getText();

        return ResponseEntity.ok(mapOutputConverter.convert(response));
    }

    @GetMapping("/prompt/authors")
    public ResponseEntity<Author> findByAuthor(@RequestParam(value = "author") String author) {
        String authorPrompt = "Generate a list of books written by the author {author} {format}";

        PromptTemplate promptTemplate = new PromptTemplate(authorPrompt);

        BeanOutputConverter<Author> beanOutputConverter = new BeanOutputConverter<>(Author.class);

        Prompt prompt = promptTemplate.create(Map.of("author", author, "format", beanOutputConverter.getFormat()));

        ChatResponse chatResponse = chatClient
            .prompt(prompt)
            .call()
            .chatResponse();

        System.out.println(chatResponse.getMetadata().getModel());

        String response = chatResponse.getResult().getOutput().getText();

        return ResponseEntity.ok(beanOutputConverter.convert(response));
    }

    @GetMapping("/prompt/authors/v2")
    public ResponseEntity<Author> findByAuthorV2(@RequestParam(value = "author") String author) {
        return ResponseEntity.ok(chatClient.prompt()
            .user(t -> t.text("Generate a list of books written by the author {author}").param("author", author))
            .call()
            .entity(Author.class));
    }

    @GetMapping("/prompt/events")
    public ResponseEntity<String> findLatestEvents(@RequestParam(value = "message", defaultValue = "Which teams participated in WTC Final 2025 and which team won the final") String message,
                                                   @RequestParam(value = "context", defaultValue = "false") boolean context) {

        PromptTemplate promptTemplate = new PromptTemplate(latestEventsPrompt);

        Map<String, Object> contextMap = new HashMap<>();

        contextMap.put("question", message);

        if (context) {
            contextMap.put("context", latestEventsContextPrompt);
        } else {
            contextMap.put("context", "");
        }

        Prompt prompt = promptTemplate.create(contextMap);

        ChatResponse chatResponse = chatClient
            .prompt(prompt)
            .call()
            .chatResponse();

        System.out.println(chatResponse.getMetadata().getModel());

        String response = chatResponse.getResult().getOutput().getText();

        return ResponseEntity.ok(response);
    }
}

package org.milan.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/olympics/faq")
public class OlympicsFaqController {

    private final ChatClient chatClient;

    private final VectorStore vectorStore;

    @Value("classpath:/prompts/rag-prompt-template.st")
    private Resource ragPromptTemplate;

    public OlympicsFaqController(OpenAiChatModel chatModel, @Qualifier("simpleVectorStore") VectorStore vectorStore) {
        this.chatClient = ChatClient.create(chatModel);
        this.vectorStore = vectorStore;
    }

    @GetMapping()
    public ResponseEntity<String> getFaq(@RequestParam(value = "message", defaultValue = "How can I buy tickets for the Olympics Games Paris 2024") String message) {
        final List<Document> similarDocuments = vectorStore.similaritySearch(SearchRequest.builder().query(message).topK(2).build());

        final List<String> contextList = similarDocuments.stream()
            .map(Document::getText)
            .toList();

        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);

        Map<String, Object> contextMap = new HashMap<>();
        contextMap.put("input", message);
        contextMap.put("documents", contextList);

        Prompt prompt = promptTemplate.create(contextMap);

        ChatResponse chatResponse = chatClient
            .prompt(prompt)
            .call()
            .chatResponse();

        String response = chatResponse.getResult().getOutput().getText();

        return ResponseEntity.ok(response);
    }
}

package org.milan.ai.command;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Milan Rathod
 */
@ShellComponent
public class SpringBootReferenceDocAssistantCommand {

    private final ChatClient chatClient;

    private final VectorStore vectorStore;

    @Value("classpath:/prompts/spring-boot-reference.st")
    private Resource springBootReference;

    public SpringBootReferenceDocAssistantCommand(OpenAiChatModel chatModel, @Qualifier("vectorStore") VectorStore vectorStore) {
        this.chatClient = ChatClient.create(chatModel);
        this.vectorStore = vectorStore;
    }

    @ShellMethod(key = "q", value = "Ask a question")
    public String question(@ShellOption(value = {"--question"}, defaultValue = "What is Spring Boot") String question) {
        PromptTemplate promptTemplate = new PromptTemplate(springBootReference);

        Map<String, Object> contextMap = new HashMap<>();
        contextMap.put("input", question);
        contextMap.put("documents", String.join("\n", findSimilarDocuments(question)));

        ChatResponse chatResponse = chatClient
            .prompt(promptTemplate.create(contextMap))
            .call()
            .chatResponse();

        return chatResponse.getResult().getOutput().getText();
    }

    private List<String> findSimilarDocuments(String question) {
        final List<Document> documents = vectorStore.similaritySearch(SearchRequest.builder().query(question).topK(3).build());

        return documents.stream().map(Document::getText).toList();
    }
}

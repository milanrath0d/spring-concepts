package org.milan.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.Media;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Milan Rathod
 */
@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ChatClient chatClient;

    public ImageController(OpenAiChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("/describe-image")
    public String describeImage() {
        final ClassPathResource imageResource = new ClassPathResource("/images/spring-ai-image-1.jpg");
        UserMessage userMessage = new UserMessage("Can you please explain what you see in the following image?",
            List.of(new Media(MimeTypeUtils.IMAGE_JPEG, imageResource)));

        Prompt prompt = new Prompt(userMessage);

        ChatResponse chatResponse = chatClient
            .prompt(prompt)
            .call()
            .chatResponse();

        return chatResponse.getResult().getOutput().getText();
    }

    @GetMapping("/describe-code")
    public String describeImageCode() {
        final ClassPathResource imageResource = new ClassPathResource("/images/spring-ai-image-2.png");
        UserMessage userMessage = new UserMessage("The following is a screenshot of some code. Can you do your best to provide a description of what this code does?",
            List.of(new Media(MimeTypeUtils.IMAGE_PNG, imageResource)));

        Prompt prompt = new Prompt(userMessage);

        ChatResponse chatResponse = chatClient
            .prompt(prompt)
            .call()
            .chatResponse();

        return chatResponse.getResult().getOutput().getText();
    }

    @GetMapping("/image-to-code")
    public String imageToCode() {
        final ClassPathResource imageResource = new ClassPathResource("/images/spring-ai-image-2.png");
        UserMessage userMessage = new UserMessage("The following is a screenshot of some code. Can you translate this from the image into text?",
            List.of(new Media(MimeTypeUtils.IMAGE_PNG, imageResource)));

        Prompt prompt = new Prompt(userMessage);

        ChatResponse chatResponse = chatClient
            .prompt(prompt)
            .call()
            .chatResponse();

        return chatResponse.getResult().getOutput().getText();
    }

}

package org.milan.ai.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Milan Rathod
 */
@Configuration
public class RagConfiguration {

    public static final Logger log = LoggerFactory.getLogger(RagConfiguration.class);

    @Value("classpath:/docs/olympics-faq.txt")
    private Resource olympicsFaq;

    @Value("vector_store.json")
    private String vectorStoreFileName;

    @Bean
    SimpleVectorStore simpleVectorStore(OpenAiEmbeddingModel embeddingModel) {
        SimpleVectorStore simpleVectorStore = SimpleVectorStore.builder(embeddingModel).build();
        final File vectorStoreFile = getVectorStoreFile();
        if (vectorStoreFile.exists()) {
            log.info("Loading vector store from file: {}", vectorStoreFile.getAbsolutePath());
            simpleVectorStore.load(vectorStoreFile);
        } else {
            log.info("Vector store file does not exist. Generating new vector store.");
            final TextReader textReader = new TextReader(olympicsFaq);
            textReader.getCustomMetadata().put("filename", "olympics-faq.txt");
            final List<Document> documents = textReader.get();
            TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
            final List<Document> splitDocuments = tokenTextSplitter.apply(documents);

            simpleVectorStore.add(splitDocuments);
            simpleVectorStore.save(vectorStoreFile);
        }
        return simpleVectorStore;
    }

    private File getVectorStoreFile() {
        Path path = Paths.get("spring-ai","src", "main", "resources", "data");
        final String absolutePath = path.toFile().getAbsolutePath() + "/" + vectorStoreFileName;
        return new File(absolutePath);
    }
}

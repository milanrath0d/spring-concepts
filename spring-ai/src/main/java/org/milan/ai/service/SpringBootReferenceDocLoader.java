package org.milan.ai.service;


import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

/**
 * @author Milan Rathod
 */
@Component
public class SpringBootReferenceDocLoader {

    private static final Logger log = LoggerFactory.getLogger(SpringBootReferenceDocLoader.class);

    private final JdbcClient jdbcClient;
    private final VectorStore vectorStore;

    @Value("classpath:/docs/spring-boot-reference.pdf")
    private Resource springBootReference;

    public SpringBootReferenceDocLoader(JdbcClient jdbcClient, @Qualifier("vectorStore") VectorStore vectorStore) {
        this.jdbcClient = jdbcClient;
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void init() {
        final Integer count = jdbcClient.sql("select count(*) from vector_store")
            .query(Integer.class)
            .single();

        log.info("Vector store count: {}", count);

        if (count == 0) {
            log.info("Loading vector store from file: {}", springBootReference.getFilename());
            final PdfDocumentReaderConfig pdfDocumentReaderConfig = PdfDocumentReaderConfig.builder()
                .withPageExtractedTextFormatter(new ExtractedTextFormatter.Builder().withNumberOfBottomTextLinesToDelete(0)
                    .withNumberOfTopPagesToSkipBeforeDelete(0).build())
                .withPagesPerDocument(1)
                .build();

            final PagePdfDocumentReader pagePdfDocumentReader = new PagePdfDocumentReader(springBootReference, pdfDocumentReaderConfig);

            final TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
            vectorStore.accept(tokenTextSplitter.apply(pagePdfDocumentReader.get()));

            log.info("Vector store loaded successfully.");
        }

    }
}

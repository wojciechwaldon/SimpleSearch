package com.wojciechwaldon.simplesearch.infrastructure;


import com.wojciechwaldon.cqrs.infrastructure.CqrsConfiguration;
import com.wojciechwaldon.simplesearch.application.Database;
import com.wojciechwaldon.simplesearch.application.find.FindDocumentsWithPhraseQueryHandler;
import com.wojciechwaldon.simplesearch.application.save.SaveDocumentListCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CqrsConfiguration.class})
class HandlersConfiguration {

    @Bean
    public FindDocumentsWithPhraseQueryHandler findDocumentsWithPhraseQueryHandler(Database database) {
        return new FindDocumentsWithPhraseQueryHandler(database);
    }

    @Bean
    public SaveDocumentListCommandHandler saveDocumentListCommandHandler(Database database) {
        return new SaveDocumentListCommandHandler(database);
    }
}

package com.wojciechwaldon.simplesearch.infrastructure;

import com.wojciechwaldon.simplesearch.infrastructure.database.SimpleSearchDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({HandlersConfiguration.class})
public class SimpleSearchConfiguration {

    @Bean
    public SimpleSearchDatabase simpleSearchDatabase() {
        return new SimpleSearchDatabase();
    }
}

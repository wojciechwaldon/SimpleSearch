package com.wojciechwaldon.simplesearch.infrastructure;

import com.wojciechwaldon.simplesearch.infrastructure.database.SimpleSearchDatabase;
import com.wojciechwaldon.simplesearch.infrastructure.database.SimpleSearchDatabaseUpdater;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({HandlersConfiguration.class})
public class SimpleSearchConfiguration {

    @Bean
    public SimpleSearchDatabaseUpdater simpleSearchDatabaseUpdater() {
        return new SimpleSearchDatabaseUpdater();
    }

    @Bean
    public SimpleSearchDatabase simpleSearchDatabase(SimpleSearchDatabaseUpdater simpleSearchDatabaseUpdater) {
        return new SimpleSearchDatabase(simpleSearchDatabaseUpdater);
    }
}

package com.wojciechwaldon.simplesearch.infrastructure;

import com.wojciechwaldon.cqrs.api.query.QueryExecutor;
import com.wojciechwaldon.simplesearch.api.find.FindDocumentsWithPhraseQuery;
import com.wojciechwaldon.simplesearch.api.find.FindDocumentsWithPhraseQueryView;
import com.wojciechwaldon.simplesearch.application.Database;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {SimpleSearchConfiguration.class})
public class FindDocumentsWithPhraseQueryViewQueryHandlerIT extends BaseHanderIT {

    @Autowired
    private QueryExecutor queryExecutor;

    @Autowired
    private Database database;

    @Test
    public void shouldFindBrownPhraseDocuments() throws Throwable {
        //given
        String phrase_brown = "brown";
        FindDocumentsWithPhraseQuery query = FindDocumentsWithPhraseQuery.of(phrase_brown);

        //when
        database.save(DOCUMENTS);

        FindDocumentsWithPhraseQueryView queryView = queryExecutor.execute(query);
        List<String> documents = queryView.getDocuments();

        //then;
        assertThat(documents).hasSize(2);
        assertEquals(DOCUMENT_1, documents.get(0));
        assertEquals(DOCUMENT_2, documents.get(1));
    }

    @Test
    public void shouldFindFoxPhraseDocuments() throws Throwable {
        //given
        String phrase_fox = "fox";
        FindDocumentsWithPhraseQuery query = FindDocumentsWithPhraseQuery.of(phrase_fox);

        //when
        database.save(DOCUMENTS);

        FindDocumentsWithPhraseQueryView queryView = queryExecutor.execute(query);
        List<String> documents = queryView.getDocuments();

        //then
        assertThat(documents).hasSize(2);
        assertEquals(DOCUMENT_1, documents.get(0));
        assertEquals(DOCUMENT_3, documents.get(1));
    }
}

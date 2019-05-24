package com.wojciechwaldon.simplesearch.infrastructure;

import com.wojciechwaldon.cqrs.api.query.QueryExecutor;
import com.wojciechwaldon.simplesearch.api.find.FindDocumentsWithPhraseQuery;
import com.wojciechwaldon.simplesearch.api.find.FindDocumentsWithPhraseQueryView;
import com.wojciechwaldon.simplesearch.application.Database;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {SimpleSearchConfiguration.class})
public class FindDocumentsWithPhraseQueryViewQueryHandlerIT {

    private static final String DOCUMENT_1 = "the brown fox jumped over the brown dog";
    private static final String DOCUMENT_2 = "the lazy brown dog sat in the corner";
    private static final String DOCUMENT_3 = "the red fox bit the lazy dog";
    private static final Set<String> DOCUMENTS = Sets.newSet(DOCUMENT_1, DOCUMENT_2, DOCUMENT_3);

    @Autowired
    private QueryExecutor queryExecutor;

    @Autowired
    private Database database;

    @Test
    public void shouldFindDocuments() throws Throwable {
        //given
        String phrase_brown = "brown";
        String phrase_fox = "fox";
        FindDocumentsWithPhraseQuery query = FindDocumentsWithPhraseQuery.of(phrase_brown);

        //when
        database.save(DOCUMENTS);

        FindDocumentsWithPhraseQueryView documents = queryExecutor.execute(query);

        //then
        assertFalse(documents.getDocuments().isEmpty());
    }
}

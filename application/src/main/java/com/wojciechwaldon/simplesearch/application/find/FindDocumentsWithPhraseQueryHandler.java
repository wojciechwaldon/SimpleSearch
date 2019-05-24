package com.wojciechwaldon.simplesearch.application.find;

import com.wojciechwaldon.cqrs.api.query.QueryHandler;
import com.wojciechwaldon.simplesearch.api.find.FindDocumentsWithPhraseQuery;
import com.wojciechwaldon.simplesearch.api.find.FindDocumentsWithPhraseQueryView;
import com.wojciechwaldon.simplesearch.application.Database;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FindDocumentsWithPhraseQueryHandler implements QueryHandler<FindDocumentsWithPhraseQuery, FindDocumentsWithPhraseQueryView> {

    private Database database;

    public FindDocumentsWithPhraseQueryView handle(FindDocumentsWithPhraseQuery findDocumentsWithPhraseQuery) throws Exception {
        return FindDocumentsWithPhraseQueryView
                .of(database.getDocumentsFor(findDocumentsWithPhraseQuery.getPhrase()));
    }
}

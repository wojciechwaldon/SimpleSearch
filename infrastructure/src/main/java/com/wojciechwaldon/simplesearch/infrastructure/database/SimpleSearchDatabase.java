package com.wojciechwaldon.simplesearch.infrastructure.database;

import com.wojciechwaldon.simplesearch.application.Database;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class SimpleSearchDatabase implements Database {

    private HashMap<String, Set<String>> phrases;
    private Tokenizator tokenizator;

    private Consumer<String> saveDocument = document -> {
        List<String> words = tokenizator.tokenize(document);

        for (String word : words) {
            putValue(document, word);
        }
    };

    public SimpleSearchDatabase() {
        phrases = new HashMap<>();
        tokenizator = new Tokenizator();
    }

    @Override
    public void save(Set<String> documents) {
        documents
                .forEach(document -> saveDocument.accept(document));
    }

    @Override
    public Set<String> getDocumentsFor(String phrase) {
        return phrases.get(phrase);
    }

    private void putValue(String document, String phrase) {
        Set<String> documentsWithPhrase = getDocumentsWithPhrase(phrase);

        documentsWithPhrase.add(document);
        updatePhraseOccurence(documentsWithPhrase, phrase);
    }

    private Set<String> getDocumentsWithPhrase(String phrase) {
        Set<String> documentsWithPhrase = phrases.get(phrase);

        if (CollectionUtils.isEmpty(documentsWithPhrase))
            documentsWithPhrase = new HashSet<>();

        return documentsWithPhrase;
    }

    private void updatePhraseOccurence(Set<String> documents, String phrase) {
        phrases.put(phrase, documents);
    }
}

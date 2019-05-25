package com.wojciechwaldon.simplesearch.infrastructure.database;

import com.wojciechwaldon.simplesearch.application.Database;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.function.Consumer;

public class SimpleSearchDatabase implements Database {

    private HashMap<String, Set<String>> phrases;
    private TFIDFGenerator tfidfGenerator;

    private Consumer<String> saveDocument = document -> {
        List<String> words = Tokenizator.tokenize(document);

        for (String word : words) {
            putValue(document, word);
        }
    };

    public SimpleSearchDatabase() {
        this.phrases = new HashMap<>();
        this.tfidfGenerator = new TFIDFGenerator();
    }

    @Override
    public void save(Set<String> documents) {
        documents
                .forEach(document -> saveDocument.accept(document));
    }

    @Override
    public List<String> getDocumentsFor(String phrase) {
        List<String> documentsForPhrase = new ArrayList<>(phrases.get(phrase));
        documentsForPhrase.sort(new PhraseComparator(tfidfGenerator, phrases, phrase));
        return documentsForPhrase;
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

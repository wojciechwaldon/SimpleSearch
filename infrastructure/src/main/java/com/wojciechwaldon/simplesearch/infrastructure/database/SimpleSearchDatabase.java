package com.wojciechwaldon.simplesearch.infrastructure.database;

import com.wojciechwaldon.simplesearch.application.Database;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Consumer;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toMap;

@RequiredArgsConstructor
public class SimpleSearchDatabase implements Database {

    @NonNull
    private SimpleSearchDatabaseUpdater updater;

    private Map<String, Map<String, Double>> phrases = new LinkedHashMap<>();

    private Consumer<String> saveDocument = document -> {
        List<String> phrases = Tokenizer.tokenize(document);

        for (String phrase : phrases) {
            put(document, phrase);
        }
    };

    @Override
    public void save(Set<String> documents) {
        documents
                .forEach(document -> saveDocument.accept(document));
        phrases = updater.updatePhrasesTFIDF(phrases);
    }

    @Override
    public List<String> getDocumentsFor(String phrase) {
        Map<String, Double> documentsForPhrase = phrases.get(phrase);

        return sorted(documentsForPhrase);
    }

    private List<String> sorted(Map<String, Double> documents) {
        Map<String, Double> sortedDocuments = documents
                .entrySet()
                .stream()
                .sorted(Entry.comparingByValue(reverseOrder()))
                .collect(
                        toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        return new ArrayList<>(sortedDocuments.keySet());
    }

    private void put(String document, String phrase) {
        Set<String> documentsWithPhrase = getDocumentsWithPhrase(phrase);

        documentsWithPhrase.add(document);
        updatePhraseOccurenceWithInitialValue(documentsWithPhrase, phrase);
    }

    private Set<String> getDocumentsWithPhrase(String phrase) {
        Map<String, Double> documentsWithPhrase = phrases.get(phrase);

        if (doesDocumentNotExist(documentsWithPhrase))
            return new HashSet<>();

        return new HashSet<>(documentsWithPhrase.keySet());
    }

    private boolean doesDocumentNotExist(Map<String, Double> documentsWithPhrase) {
        return documentsWithPhrase == null || documentsWithPhrase.isEmpty();
    }

    private void updatePhraseOccurenceWithInitialValue(Set<String> documents, String phrase) {
        Map<String, Double> documentsForPhrase = documents.stream()
                .collect(toMap(document -> document, value -> 0.D));

        phrases.put(phrase, documentsForPhrase);
    }
}

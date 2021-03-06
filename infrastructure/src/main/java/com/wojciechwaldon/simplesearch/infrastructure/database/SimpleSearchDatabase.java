package com.wojciechwaldon.simplesearch.infrastructure.database;

import com.wojciechwaldon.simplesearch.application.Database;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.Map.Entry;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toMap;

@RequiredArgsConstructor
public class SimpleSearchDatabase implements Database {

    @NonNull
    private SimpleSearchDatabaseUpdater updater;

    /*
        Map<Phrase<Map<Document,TFIDF>>
        Phrase occurs in documents with certain TFIDF
     */
    private Map<String, Map<String, Double>> phrases = new LinkedHashMap<>();

    @Override
    public void save(Set<String> documents) {
        documents
                .forEach(this::saveDocument);
        phrases = updater.updatePhrasesTFIDF(phrases);
    }

    @Override
    public List<String> getDocumentsFor(String phrase) {
        Map<String, Double> documentsForPhrase = phrases.get(phrase);

        return sorted(documentsForPhrase);
    }

    private void saveDocument(String document) {
        List<String> phrases = Tokenizer.tokenize(document);

        for (String phrase : phrases) {
            put(document, phrase);
        }
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

        return doesDocumentExist(documentsWithPhrase)
                ? new HashSet<>(documentsWithPhrase.keySet())
                : new HashSet<>();
    }

    private boolean doesDocumentExist(Map<String, Double> documentsWithPhrase) {
        return documentsWithPhrase != null && !documentsWithPhrase.isEmpty();
    }

    private void updatePhraseOccurenceWithInitialValue(Set<String> documents, String phrase) {
        Map<String, Double> documentsForPhrase = documents.stream()
                .collect(toMap(document -> document, value -> 0.D));

        phrases.put(phrase, documentsForPhrase);
    }
}

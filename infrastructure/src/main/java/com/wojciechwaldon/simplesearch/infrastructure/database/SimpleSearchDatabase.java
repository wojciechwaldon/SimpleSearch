package com.wojciechwaldon.simplesearch.infrastructure.database;

import com.wojciechwaldon.simplesearch.application.Database;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Consumer;

import static java.util.Comparator.*;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class SimpleSearchDatabase implements Database {

    private Map<String, Map<String, Double>> phrases = new LinkedHashMap<>();

    private Consumer<String> saveDocument = document -> {
        List<String> words = Tokenizer.tokenize(document);

        for (String word : words) {
            putValue(document, word);
        }
    };

    @Override
    public void save(Set<String> documents) {
        documents
                .forEach(document -> saveDocument.accept(document));
        updatePhrasesTFIDF();
    }

    @Override
    public List<String> getDocumentsFor(String phrase) {
        Map<String, Double> documents = phrases.get(phrase)
                .entrySet()
                .stream()
                .sorted(Entry.comparingByValue(reverseOrder()))
                .collect(
                        toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        return new ArrayList<>(documents.keySet());
    }

    private void putValue(String document, String phrase) {
        Set<String> documentsWithPhrase = getDocumentsWithPhrase(phrase);

        documentsWithPhrase.add(document);
        updatePhraseOccurence(documentsWithPhrase, phrase);
    }

    //todo two returns
    private Set<String> getDocumentsWithPhrase(String phrase) {
        Map<String, Double> documentsWithPhrase = phrases.get(phrase);

        if (doesDocumentNotExist(documentsWithPhrase))
            return new HashSet<>();

        return new HashSet<>(documentsWithPhrase.keySet());
    }

    private boolean doesDocumentNotExist(Map<String, Double> documentsWithPhrase) {
        return documentsWithPhrase == null || documentsWithPhrase.isEmpty();
    }

    private void updatePhraseOccurence(Set<String> documents, String phrase) {
        Map<String, Double> documentsForPhrase = documents.stream()
                .collect(toMap(document -> document, value -> 0.D));

        phrases.put(phrase, documentsForPhrase);
    }

    private void updatePhrasesTFIDF() {
        Map<String, Map<String, Double>> updatedPhrases = new LinkedHashMap<>();
        for (Entry<String, Map<String, Double>> phrase : phrases.entrySet()) {
            String currentPhrase = phrase.getKey();
            Map<String, Double> documentsWithPhrase = phrase.getValue();

            documentsWithPhrase.forEach((currentDocument, value) -> {

                Double TFIDF = TFIDFGenerator.generateFor(currentDocument, phrases, currentPhrase);

                if (updatedPhrases.containsKey(currentPhrase)) {
                    updatedPhrases.get(currentPhrase).put(currentDocument, TFIDF);
                } else {
                    Map<String, Double> updatedDocument = new HashMap<String, Double>() {{
                        put(currentDocument, TFIDF);
                    }};

                    updatedPhrases.put(currentPhrase, updatedDocument);
                }
                // updatedPhrases.put(currentPhrase, updatedPhrases.containsKey(currentPhrase) ? updatedPhrases.get(currentPhrase).put(currentDocument, TFIDF) : updatedDocument);
                //updatedPhrases.computeIfPresent(currentPhrase, (k, v) -> v.put(currentDocument, TFIDF));
            });
        }
        phrases = updatedPhrases;
    }
}

package com.wojciechwaldon.simplesearch.infrastructure.database;

import com.wojciechwaldon.simplesearch.application.Database;

import java.util.*;
import java.util.function.Consumer;

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
                .sorted(comparingByValue())
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
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
        //List<String> overallDocuments = phrases.
//        phrases.entrySet()
//                .stream()
//                .map(p -> p.)
    }
}

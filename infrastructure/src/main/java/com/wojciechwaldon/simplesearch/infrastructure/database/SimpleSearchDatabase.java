package com.wojciechwaldon.simplesearch.infrastructure.database;

import com.wojciechwaldon.simplesearch.application.Database;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SimpleSearchDatabase implements Database {

    private HashMap<String, List<Phrase>> phrasesInDocument;
    private TFIDFGenerator tfidfGenerator;

    private Consumer<String> saveDocument = document -> {
        List<String> words = Tokenizator.tokenize(document);
        List<Phrase> phrases = words.stream()
                .map(Phrase::of)
                .collect(Collectors.toList());

        putValue(document, phrases);
        updatePhraseTFIDF();
    };

    public SimpleSearchDatabase() {
        this.phrasesInDocument = new HashMap<>();
        this.tfidfGenerator = new TFIDFGenerator();
    }

    @Override
    public void save(Set<String> documents) {
        documents
                .forEach(document -> saveDocument.accept(document));
    }

    @Override
    public List<String> getDocumentsFor(String phrase) {
        Map<String, Phrase> documentsForPhrase = new HashMap<>();
        for (Map.Entry<String, List<Phrase>> document : phrasesInDocument.entrySet()) {
            for (Phrase phraseInDocument : document.getValue()) {
                if (phraseInDocument.value.equalsIgnoreCase(phrase))
                    documentsForPhrase.put(document.getKey(), phraseInDocument);
            }
        }
        return phrasesInDocument.entrySet().stream()
                .sorted(TFIDFComparator.of(phrase))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private void putValue(String document, List<Phrase> phrases) {
        phrasesInDocument.put(document, phrases);
    }

    private void updatePhraseTFIDF() {
        Set<String> documents = new HashSet<>(phrasesInDocument.keySet());

        for (Map.Entry<String, List<Phrase>> document : phrasesInDocument.entrySet()) {
            for (Phrase phraseInDocument : document.getValue()) {
                double IFIDF = tfidfGenerator.generateFor(document.getKey(), documents, phraseInDocument);

                phraseInDocument.updateTFIDF(IFIDF);
            }
        }
    }
}

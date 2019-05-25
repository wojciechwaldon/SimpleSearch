package com.wojciechwaldon.simplesearch.infrastructure.database;

import com.wojciechwaldon.simplesearch.application.Database;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SimpleSearchDatabase implements Database {

    private HashSet<Document> documents = new HashSet<>();

    private Consumer<String> saveDocument = document -> {
        documents.add(Document.of(document));

        updatePhraseTFIDF();
    };

    @Override
    public void save(Set<String> documents) {
        documents
                .forEach(document -> saveDocument.accept(document));
    }

    @Override
    public List<String> getDocumentsFor(String phrase) {
        List<Document> documentsWithPhrase = new ArrayList<>();

        for (Document document : documents) {
            boolean hasPhrase = document.getPhrases().stream()
                    .anyMatch((phrase::equalsIgnoreCase));

            if (hasPhrase)
                documentsWithPhrase.add(document);
        }

        return documentsSortedByTFIDF(documentsWithPhrase, phrase);
    }

    private List<@NonNull String> documentsSortedByTFIDF(List<Document> documentsWithPhrase, String phrase) {
        documentsWithPhrase.sort(DocumentComparator.of(phrase));

        return documentsWithPhrase.stream()
                .map(Document::getContent)
                .collect(Collectors.toList());
    }

    private void updatePhraseTFIDF() {
        for (Document document : documents) {
            for (String phrase : document.getPhrases()) {
                double TFIDF = TFIDFGenerator.generateFor(document.getPhrases(), documents, phrase);

                document.updateTFIDF(phrase, TFIDF);
            }
        }
    }
}

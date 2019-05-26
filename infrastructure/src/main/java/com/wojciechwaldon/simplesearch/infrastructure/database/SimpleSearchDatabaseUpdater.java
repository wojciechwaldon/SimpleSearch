package com.wojciechwaldon.simplesearch.infrastructure.database;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class SimpleSearchDatabaseUpdater {

    Map<String, Map<String, Double>> updatePhrasesTFIDF(Map<String, Map<String, Double>> phrases) {
        Map<String, Map<String, Double>> updatedPhrases = new LinkedHashMap<>();

        phrases.forEach((currentPhrase, documentsWithPhrase) ->
                documentsWithPhrase.forEach((currentDocument, value) ->
                        updatePhrasesFor(phrases, updatedPhrases, currentDocument, currentPhrase)
                ));

        return updatedPhrases;
    }

    private void updatePhrasesFor(Map<String, Map<String, Double>> phrases,
                                  Map<String, Map<String, Double>> updatedPhrases,
                                  String currentDocument,
                                  String currentPhrase) {
        Double TFIDF = TFIDFGenerator.generateFor(currentDocument, phrases, currentPhrase);

        Map<String, Double> updatedDocumentForPhrase = updatedPhrases.getOrDefault(currentPhrase, new HashMap<>());
        updatedDocumentForPhrase.put(currentDocument, TFIDF);

        updatedPhrases.put(currentPhrase, updatedDocumentForPhrase);
    }
}

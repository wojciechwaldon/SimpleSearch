package com.wojciechwaldon.simplesearch.infrastructure.database;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class PhraseParser {

    static Set<String> prepareDocumentsFrom(Map<String, Map<String, Double>> phrases) {
        Set<String> documents = new HashSet<>();

        for (Map<String, Double> documentsPerPhrase : phrases.values()) {
            documents.addAll(documentsPerPhrase.keySet());
        }

        return documents;
    }
}

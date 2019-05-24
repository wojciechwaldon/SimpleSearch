package com.wojciechwaldon.simplesearch.infrastructure.database;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class TFIDFGenerator {

    private Tokenizator tokenizator;

    TFIDFGenerator(Tokenizator tokenizator) {
        this.tokenizator = tokenizator;
    }

    double generateFor(String document, HashMap<String, Set<String>> phrases, String phrase) {
        return tf(tokenizator.tokenize(document), phrase) * idf(phrases, phrase);
    }

    // number of times phrase appears in the document (phrases) / total number of phrase in the document(phrases)
    private double tf(List<String> phrases, String phrase) {
        long phraseInDocument = phrases.stream()
                .filter(phrase::equalsIgnoreCase)
                .count();
        return (double)phraseInDocument / (double)phrases.size();
    }

    // number of documents (phrases) / number of documents (phrases) that contain the phrase
    private double idf(HashMap<String, Set<String>> phrases, String phrase) {
        double phraseInOverall = 0;
        Set<String> documents = phrases.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        for(String document: documents) {
            for(String phraseInDocument: tokenizator.tokenize(document)) {
               if (phraseInDocument.equalsIgnoreCase(phrase)) {
                   phraseInOverall++;
                   break;
               }
            }
        }
        return Math.log(documents.size() / phraseInOverall);

    }
}

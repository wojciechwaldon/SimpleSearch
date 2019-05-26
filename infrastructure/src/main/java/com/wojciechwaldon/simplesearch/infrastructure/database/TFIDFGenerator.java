package com.wojciechwaldon.simplesearch.infrastructure.database;

import java.util.*;

class TFIDFGenerator {

    static double generateFor(String document, Map<String, Map<String, Double>> documentsWithPhrases, String phrase) {
        return tf(Tokenizer.tokenize(document), phrase) * idf(documentsWithPhrases, phrase);
    }

    // number of times phrase appears in the document
    private static double tf(List<String> phrases, String phrase) {
        return (double) phrases.stream()
                .filter(phrase::equalsIgnoreCase)
                .count();
    }

    // number of documents (phrases) / number of documents (phrases) that contain the phrase
    private static double idf(Map<String, Map<String, Double>> documentsWithPhrases, String phrase) {
        double phraseInOverall = 0;
        Set<String> documents = prepareDocumentsFrom(documentsWithPhrases);

        for (String document : documents) {
            for (String phraseInDocument : Tokenizer.tokenize(document)) {
                if (phraseInDocument.equalsIgnoreCase(phrase)) {
                    phraseInOverall++;
                    break;
                }
            }
        }
        return Math.log(documents.size() / phraseInOverall);

    }

    private static Set<String> prepareDocumentsFrom(Map<String, Map<String, Double>> phrases) {
        Set<String> documents = new HashSet<>();

        for (Map<String, Double> xd : new ArrayList<>(phrases.values())) {
            documents.addAll(xd.keySet());
        }

        return documents;
    }
}

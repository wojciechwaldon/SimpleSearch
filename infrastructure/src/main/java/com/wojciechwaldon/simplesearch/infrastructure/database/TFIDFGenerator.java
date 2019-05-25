package com.wojciechwaldon.simplesearch.infrastructure.database;

import java.util.List;
import java.util.Set;

class TFIDFGenerator {

    double generateFor(String document, Set<String> documents, Phrase phrase) {
        return tf(Tokenizator.tokenize(document), phrase.value) * idf(documents, phrase.value);
    }

    // number of times phrase appears in the document
    private double tf(List<String> phrases, String phrase) {
        return (double) phrases.stream()
                .filter(phrase::equalsIgnoreCase)
                .count();
    }

    // Math.log(number of documents (phrases) / number of documents (phrases) that contain the phrase)
    private double idf(Set<String> documents, String phrase) {
        double phraseInOverall = 0;
        for (String document : documents) {
            for (String phraseInDocument : Tokenizator.tokenize(document)) {
                if (phraseInDocument.equalsIgnoreCase(phrase)) {
                    phraseInOverall++;
                    break;
                }
            }
        }
        return Math.log(documents.size() / phraseInOverall);

    }
}

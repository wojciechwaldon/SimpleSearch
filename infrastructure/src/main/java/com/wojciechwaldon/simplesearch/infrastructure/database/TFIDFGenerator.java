package com.wojciechwaldon.simplesearch.infrastructure.database;

import java.util.List;
import java.util.Set;

class TFIDFGenerator {

    static double generateFor(List<String> documentWords, Set<Document> documents, String phrase) {
        return tf(documentWords, phrase) * idf(documents, phrase);
    }

    // number of times phrase appears in the document
    private static double tf(List<String> phrases, String phrase) {
        return (double) phrases.stream()
                .filter(phrase::equalsIgnoreCase)
                .count();
    }

    // Math.log(number of documents (phrases) / number of documents (phrases) that contain the phrase)
    private static double idf(Set<Document> documents, String phrase) {
        double phraseInOverall = 0;
        for (Document document : documents) {
            for (String phraseInDocument : document.getPhrases()) {
                if (phraseInDocument.equalsIgnoreCase(phrase)) {
                    phraseInOverall++;
                    break;
                }
            }
        }

        return Math.log(documents.size() / phraseInOverall);

    }
}

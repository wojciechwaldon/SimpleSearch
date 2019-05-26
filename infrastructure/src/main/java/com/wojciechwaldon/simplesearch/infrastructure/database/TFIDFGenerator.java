package com.wojciechwaldon.simplesearch.infrastructure.database;

import java.util.List;
import java.util.Map;
import java.util.Set;

class TFIDFGenerator {

    static double generateFor(String document, Map<String, Map<String, Double>> phrasesWithDocuments, String phrase) {
        return tf(Tokenizer.tokenize(document), phrase) * idf(phrasesWithDocuments, phrase);
    }

    // number of times phrase appears in the document
    private static double tf(List<String> phrases, String phrase) {
        return (double) phrases.stream()
                .filter(phrase::equalsIgnoreCase)
                .count();
    }

    // number of documents (phrases) / number of documents (phrases) that contain the phrase
    private static double idf(Map<String, Map<String, Double>> phrasesWithDocuments, String phrase) {
        double phraseInOverall = 0;
        Set<String> documents = PhraseParser.prepareDocumentsFrom(phrasesWithDocuments);

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
}

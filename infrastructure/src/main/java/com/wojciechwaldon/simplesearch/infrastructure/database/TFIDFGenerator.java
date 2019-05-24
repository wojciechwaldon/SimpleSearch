package com.wojciechwaldon.simplesearch.infrastructure.database;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

class TFIDFGenerator {

    private Tokenizator tokenizator;

    public double generateFor(String document, HashMap<String, Set<String>> phrases, String phrase) {
        return tf(tokenizator.tokenize(document), phrase) * idf(phrases, phrase);
    }

    private double tf(List<String> doc, String term) {
        return 0;
    }

    private double idf(HashMap<String, Set<String>> phrases, String term) {
        return 0;
    }
}

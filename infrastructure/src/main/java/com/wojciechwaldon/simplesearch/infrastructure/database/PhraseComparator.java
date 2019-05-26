package com.wojciechwaldon.simplesearch.infrastructure.database;

import java.util.Comparator;
import java.util.Map;

public class PhraseComparator implements Comparator<String> {
    private Map<String, Map<String, Double>> phrases;
    private String phrase;

    PhraseComparator(Map<String, Map<String, Double>> phrases, String phrase) {
        this.phrases = phrases;
        this.phrase = phrase;
    }

    @Override
    public int compare(String o1, String o2) {
        double o1Value = TFIDFGenerator.generateFor(o1, phrases, phrase);
        double o2Value = TFIDFGenerator.generateFor(o2, phrases, phrase);

        return Double.compare(o2Value, o1Value);
    }
}

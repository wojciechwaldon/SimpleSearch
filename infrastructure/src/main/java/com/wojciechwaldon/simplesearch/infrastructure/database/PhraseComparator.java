package com.wojciechwaldon.simplesearch.infrastructure.database;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

public class PhraseComparator implements Comparator<String> {

    private TFIDFGenerator tfidfGenerator;
    private HashMap<String, Set<String>> phrases;
    private String phrase;

    PhraseComparator(TFIDFGenerator tfidfGenerator, HashMap<String, Set<String>> phrases, String phrase) {
        this.tfidfGenerator = tfidfGenerator;
        this.phrases = phrases;
        this.phrase = phrase;
    }

    @Override
    public int compare(String o1, String o2) {
        double o1Value = tfidfGenerator.generateFor(o1, phrases, phrase);
        double o2Value = tfidfGenerator.generateFor(o2, phrases, phrase);

        return Double.compare(o2Value, o1Value);

//        if(o1Value > o2Value) return -1;
//        if(o1Value < o2Value) return 1;
//        return 0;
        //  return Double.compare(o1Value, o2Value);
    }
}

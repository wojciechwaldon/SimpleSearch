package com.wojciechwaldon.simplesearch.infrastructure.database;

import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@AllArgsConstructor(staticName = "of")
public class TFIDFComparator implements Comparator<Map.Entry<String, List<Phrase>>> {

    private final String phrase;

    //Sort in a decending order by TFIDF score
    public int compare(Map.Entry<String, List<Phrase>> phrases1, Map.Entry<String, List<Phrase>> phrases2) {
        if()
    }
}

package com.wojciechwaldon.simplesearch.infrastructure.database;

import lombok.AllArgsConstructor;

import java.util.Comparator;

@AllArgsConstructor(staticName = "of")
class DocumentComparator implements Comparator<Document> {

    private String phrase;

    @Override
    public int compare(Document o1, Document o2) {
        return Double.compare(o2.getTfidf(phrase), o1.getTfidf(phrase));
    }
}

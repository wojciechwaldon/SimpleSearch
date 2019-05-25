package com.wojciechwaldon.simplesearch.infrastructure.database;

import lombok.Getter;
import lombok.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
class Document {

    @NonNull
    private String content;

    private List<String> phrases;
    private Map<String, Double> tfidf = new HashMap<>();

    private Document(String content, List<String> phrases) {
        this.content = content;
        this.phrases = phrases;
    }

    static Document of(String content) {
        return new Document(content, Tokenizator.tokenize(content));
    }

    Double getTfidf(String phrase) {
        return tfidf.get(phrase);
    }

    void updateTFIDF(String phrase, double value) {
        tfidf.put(phrase, value);
    }
}

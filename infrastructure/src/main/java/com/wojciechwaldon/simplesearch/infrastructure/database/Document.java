package com.wojciechwaldon.simplesearch.infrastructure.database;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
class Document {

    @NonNull
    private String content;

    @NonNull
    private List<String> phrases;

    @Getter(AccessLevel.NONE)
    private Map<String, Double> tfidf = new HashMap<>();

    static Document of(String content) {
        return new Document(content, Tokenizer.tokenize(content));
    }

    Double getTfidf(String phrase) {
        return tfidf.get(phrase);
    }

    void updateTFIDF(String phrase, double value) {
        tfidf.put(phrase, value);
    }

    @Override
    public boolean equals(Object obj) {
        return content.equalsIgnoreCase(((Document) obj).content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}

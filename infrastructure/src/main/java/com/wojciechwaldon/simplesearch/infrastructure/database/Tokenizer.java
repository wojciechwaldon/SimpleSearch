package com.wojciechwaldon.simplesearch.infrastructure.database;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* Class created for the purposes of eventual change in tokenize algorithm (Dependency inversion) */
class Tokenizer {

    static List<String> tokenize(String document) {
        List<String> phrases = new ArrayList<>();
        StringTokenizer multiTokenizer = new StringTokenizer(document);

        while (multiTokenizer.hasMoreTokens()) {
            phrases.add(multiTokenizer.nextToken());
        }
        return phrases;
    }
}

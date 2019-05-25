package com.wojciechwaldon.simplesearch.infrastructure.database;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Class created for the purposes of eventual change in tokenize algorithm (Dependency inversion) */
class Tokenizer {

    private static final Pattern PATTERN = Pattern.compile("\\w+");

    static List<String> tokenize(String document) {
        List<String> phrases = new ArrayList<>();

        Matcher matcher = PATTERN.matcher(document);
        while (matcher.find()) {
            phrases.add(matcher.group());
        }
        return phrases;
    }
}

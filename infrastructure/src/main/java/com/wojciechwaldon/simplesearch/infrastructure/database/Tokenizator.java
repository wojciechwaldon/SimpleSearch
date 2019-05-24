package com.wojciechwaldon.simplesearch.infrastructure.database;

import java.util.Arrays;
import java.util.List;

/* Class created for the purposes of eventual change in tokenize algorithm (Dependency inversion) */
class Tokenizator {

    //todo regexp ??
    List<String> tokenize(String document) {
        return Arrays.asList(document.split("\\W+"));
    }
}

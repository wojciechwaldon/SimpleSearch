package com.wojciechwaldon.simplesearch.application;

import java.util.Set;

public interface Database {

    void save(Set<String> documents);

    Set<String> getDocumentsFor(String phrase);
}

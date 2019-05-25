package com.wojciechwaldon.simplesearch.application;

import java.util.List;
import java.util.Set;

public interface Database {

    void save(Set<String> documents);

    List<String> getDocumentsFor(String phrase);

    void deleteAll();
}

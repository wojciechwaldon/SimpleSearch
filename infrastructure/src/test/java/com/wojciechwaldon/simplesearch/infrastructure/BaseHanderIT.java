package com.wojciechwaldon.simplesearch.infrastructure;

import org.mockito.internal.util.collections.Sets;

import java.util.Set;

abstract class BaseHanderIT {

    protected static final String DOCUMENT_1 = "the brown fox jumped over the brown dog";
    protected static final String DOCUMENT_2 = "the lazy brown dog sat in the corner";
    protected static final String DOCUMENT_3 = "the red fox bit the lazy dog";
    protected static final Set<String> DOCUMENTS = Sets.newSet(DOCUMENT_1, DOCUMENT_2, DOCUMENT_3);
}

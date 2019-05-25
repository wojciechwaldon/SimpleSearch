package com.wojciechwaldon.simplesearch.infrastructure.database;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
@Getter
class Phrase {

    @NonNull
    String value;
    double tfIdf = 0;

    void updateTFIDF(double value) {
        this.tfIdf = value;
    }
}

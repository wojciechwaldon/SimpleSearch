package com.wojciechwaldon.simplesearch.api.find;

import com.wojciechwaldon.cqrs.api.query.Query;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class FindDocumentsWithPhraseQuery implements Query<FindDocumentsWithPhraseQueryView> {

    @NonNull
    private String phrase;
}

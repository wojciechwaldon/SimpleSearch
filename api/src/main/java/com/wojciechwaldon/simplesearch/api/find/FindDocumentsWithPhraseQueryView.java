package com.wojciechwaldon.simplesearch.api.find;

import com.wojciechwaldon.cqrs.api.query.QueryView;
import lombok.*;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class FindDocumentsWithPhraseQueryView implements QueryView {

    Set<String> documents;
}

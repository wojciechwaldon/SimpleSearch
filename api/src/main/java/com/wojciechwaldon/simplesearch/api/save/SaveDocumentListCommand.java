package com.wojciechwaldon.simplesearch.api.save;

import com.wojciechwaldon.cqrs.api.command.Command;
import lombok.*;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(staticName = "of")
@Getter
@ToString
public class SaveDocumentListCommand implements Command {

    @NonNull
    private Set<String> documents;
}

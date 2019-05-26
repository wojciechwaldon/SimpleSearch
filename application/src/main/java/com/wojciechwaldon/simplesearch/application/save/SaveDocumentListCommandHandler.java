package com.wojciechwaldon.simplesearch.application.save;

import com.wojciechwaldon.cqrs.api.command.CommandHandler;
import com.wojciechwaldon.simplesearch.api.save.SaveDocumentListCommand;
import com.wojciechwaldon.simplesearch.application.Database;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SaveDocumentListCommandHandler implements CommandHandler<SaveDocumentListCommand> {

    private Database database;

    public void handle(SaveDocumentListCommand saveDocumentListCommand) {
        database.save(saveDocumentListCommand.getDocuments());
    }
}

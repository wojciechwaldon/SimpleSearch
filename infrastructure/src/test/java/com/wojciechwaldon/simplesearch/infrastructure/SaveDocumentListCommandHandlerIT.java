package com.wojciechwaldon.simplesearch.infrastructure;

import com.wojciechwaldon.cqrs.api.command.CommandExecutor;
import com.wojciechwaldon.simplesearch.api.save.SaveDocumentListCommand;
import com.wojciechwaldon.simplesearch.application.Database;
import com.wojciechwaldon.simplesearch.infrastructure.database.SimpleSearchDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {SimpleSearchConfiguration.class})
public class SaveDocumentListCommandHandlerIT extends BaseHanderIT{

    @Autowired
    private CommandExecutor commmandExecutor;

    @Autowired
    private Database database;

    @Test
    public void shouldSaveDocuments() throws Throwable {
        //given
        SaveDocumentListCommand command = SaveDocumentListCommand.of(DOCUMENTS);

        //when
        commmandExecutor.execute(command);

        //then
        assertFalse(database.getDocumentsFor("Test").isEmpty());
        assertFalse(database.getDocumentsFor("dokument").isEmpty());
    }
}

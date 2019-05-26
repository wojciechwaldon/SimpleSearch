package com.wojciechwaldon.simplesearch.infrastructure;

import com.wojciechwaldon.cqrs.api.command.CommandExecutor;
import com.wojciechwaldon.simplesearch.api.save.SaveDocumentListCommand;
import com.wojciechwaldon.simplesearch.application.Database;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {SimpleSearchConfiguration.class})
public class SaveDocumentListCommandHandlerIT extends BaseHandlerIT {

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
        assertFalse(database.getDocumentsFor("brown").isEmpty());
        assertFalse(database.getDocumentsFor("fox").isEmpty());
    }
}

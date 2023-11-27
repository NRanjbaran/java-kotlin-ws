package service;

import model.dto.WriterEventDto;
import model.dto.WriterEventType;
import model.entity.Book;
import model.entity.Writer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.HibernateFunctionExecutorImpl;
import repository.WriterDao;
import util.DataBaseUtil;

import java.util.List;
import java.util.Optional;

class WriterStreamProcessorImplIntegrationTest {

    private DataBaseUtil dataBaseUtil;

    @BeforeEach
    void init() {
        dataBaseUtil = new DataBaseUtil();
    }


    @Test
    void testProcess_shouldAddValidWriterEventDto_addWriter() {
        String code = "1";
        String description = "Test1";
        String firstName = "firstName";
        String lastName = "lastName";
        List<Book> books = List.of();
        WriterEventDto writerEventDto = new WriterEventDto(
                code,
                description,
                firstName,
                lastName,
                WriterEventType.ADD,
                books
        );
        WriterDao writerDao = new WriterDao(HibernateFunctionExecutorImpl.newInstance());

        new WriterStreamProcessorImpl(writerDao).process(writerEventDto);

        Optional<Writer> writer = this.dataBaseUtil.findWriter("1");
        Assertions.assertNotNull(writer.orElse(null));
    }
}
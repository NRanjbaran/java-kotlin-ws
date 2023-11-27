package service;

import model.dto.BookEventDto;
import model.entity.Book;
import model.entity.Writer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.HibernateFunctionExecutorImpl;
import repository.WriterDao;
import repository.BookDao;
import util.DataBaseUtil;

import java.util.List;
import java.util.Optional;

class BookStreamProcessorImplIntegrationTest {

    private DataBaseUtil dataBaseUtil;

    @BeforeEach
    void init() {
        this.dataBaseUtil = new DataBaseUtil();
        this.dataBaseUtil.deleteAllTables();
    }

    @Test
    void testProcess_validQuoteEventDto_saveQuote() {
        Double price = 1000d;
        Writer writer = this.dataBaseUtil.createAndSaveDefaultWriter();
        HibernateFunctionExecutorImpl hibernateFunctionExecutor = HibernateFunctionExecutorImpl.newInstance();
        BookEventDto bookEventDto = new BookEventDto(price, writer.getCode());
        BookDao bookDao = new BookDao(hibernateFunctionExecutor);
        WriterDao writerDao = new WriterDao(hibernateFunctionExecutor);

        new BookStreamProcessorImpl(writerDao, bookDao).process(bookEventDto);

        Optional<List<Book>> quotes = this.dataBaseUtil.findQuotes(writer.getCode());
        Assertions.assertNotNull(quotes.orElse(null));
        Assertions.assertEquals(price, quotes.orElse(null).get(0).getPrice());
    }

    @Test
    void testProcess_quoteEventDtoWithInvalidCODE_notSavedQuote() {
        Double price = 1000d;
        String code = "Dummy";
        HibernateFunctionExecutorImpl hibernateFunctionExecutor = HibernateFunctionExecutorImpl.newInstance();
        BookEventDto bookEventDto = new BookEventDto(price, code);
        BookDao bookDao = new BookDao(hibernateFunctionExecutor);
        WriterDao writerDao = new WriterDao(hibernateFunctionExecutor);

        new BookStreamProcessorImpl(writerDao, bookDao).process(bookEventDto);

        Optional<List<Book>> quotes = this.dataBaseUtil.findQuotes(code);
        Assertions.assertEquals(0, quotes.orElse(List.of()).size());
    }

}
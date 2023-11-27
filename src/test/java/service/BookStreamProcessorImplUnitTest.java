package service;

import model.dto.BookEventDto;
import model.entity.Writer;
import model.entity.Book;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repository.WriterDao;
import repository.BookDao;

import java.util.Optional;

class BookStreamProcessorImplUnitTest {

    @Test
    void testProcess_validQuoteEventDto_verifyMethods() {
        double price = 2d;
        String code = "code test";
        BookEventDto bookEventDto = new BookEventDto(price, code);
        WriterDao writerDao = Mockito.mock(WriterDao.class);
        BookDao bookDao = Mockito.mock(BookDao.class);
        Mockito.when(writerDao.findWriter(Mockito.anyString())).thenReturn(Optional.of(new Writer()));

        new BookStreamProcessorImpl(writerDao, bookDao).process(bookEventDto);

        Mockito.verify(writerDao).findWriter(code);
        Mockito.verify(bookDao).save(Mockito.any(Book.class));
    }

}
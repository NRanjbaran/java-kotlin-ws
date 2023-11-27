package util;

import model.entity.Book;
import model.entity.Writer;
import repository.HibernateFunctionExecutor;
import repository.GeneralRepository;
import repository.HibernateFunctionExecutorImpl;
import repository.WriterDao;
import repository.BookDao;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DataBaseUtil {

    public void deleteAllTables() {
        List<String> tables = List.of("Writer", "Quote");
        HibernateFunctionExecutor hibernateFunctionExecutor = HibernateFunctionExecutorImpl.newInstance();
        GeneralRepository repository = new GeneralRepository(hibernateFunctionExecutor);
        repository.truncateTables(tables);
    }

    public Writer createAndSaveDefaultWriter() {
        Writer writer = new EntityUtil().createDefaultWriter();
        this.saveWriter(writer);
        return writer;
    }

    public void saveWriter(Writer writer) {
        HibernateFunctionExecutor hibernateFunctionExecutor = HibernateFunctionExecutorImpl.newInstance();
        WriterDao writerRepository = new WriterDao(hibernateFunctionExecutor);
        writerRepository.save(writer);
    }

    public void saveQuotesByInstants(List<Instant> instants, Writer writer, int instantCountPerMinute) {
        Collections.sort(instants);
        for (int i = 1; i <= instants.size(); i++) {
            Book book = new Book();
            book.setWriter(writer);
            book.setPrice(getPrice(i, instantCountPerMinute));
            book.setPublishedDateTime(instants.get(i - 1));
            this.saveQuote(book);
        }
    }

    public void saveQuoteWithInstant(Instant instant, Writer writer) {
        Book book = new Book();
        book.setWriter(writer);
        book.setPrice(this.getPrice(2, 5));
        book.setPublishedDateTime(instant);
        this.saveQuote(book);
    }

    public Double getPrice(int index, int instantCountPerMinute) {
        double rem = index % instantCountPerMinute;
        if (rem == 0) {
            return 5d;
        }
        return rem;
    }
    public void saveQuote(Book book) {
        HibernateFunctionExecutor hibernateFunctionExecutor = HibernateFunctionExecutorImpl.newInstance();
        BookDao bookDao = new BookDao(hibernateFunctionExecutor);
        bookDao.save(book);
    }

    public Optional<Writer> findWriter(String code) {
        HibernateFunctionExecutor hibernateFunctionExecutor = HibernateFunctionExecutorImpl.newInstance();
        WriterDao writerDao = new WriterDao(hibernateFunctionExecutor);
        return writerDao.findWriter(code);
    }

    public Optional<List<Book>> findQuotes(String code) {
        HibernateFunctionExecutor hibernateFunctionExecutor = HibernateFunctionExecutorImpl.newInstance();
        BookDao bookDao = new BookDao(hibernateFunctionExecutor);
        return bookDao.findBooks(code);
    }
}

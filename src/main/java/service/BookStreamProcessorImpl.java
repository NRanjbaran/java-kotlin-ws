package service;

import model.dto.BookEventDto;
import model.entity.Writer;
import model.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.WriterDao;
import repository.BookDao;
import util.EntityMapper;

import java.util.Optional;

/**
 * An implementation of the {@link BookStreamProcessor} interface that processes
 * quote event data by saving the associated quote information.
 */
public class BookStreamProcessorImpl implements BookStreamProcessor {

    private static final Logger logger = LoggerFactory.getLogger(BookStreamProcessorImpl.class);
    private final WriterDao writerDao;

    private final BookDao bookDao;

    /**
     * Constructs a new instance of QuoteStreamProcessorImpl with the provided WriterDao and QuoteDao.
     *
     * @param writerDao The DAO (Data Access Object) used to interact with the persistence layer
     *                      for retrieving writer data. Must not be null.
     * @param bookDao      The DAO used to interact with the persistence layer for saving quote data.
     *                      Must not be null.
     */
    public BookStreamProcessorImpl(WriterDao writerDao, BookDao bookDao) {
        this.writerDao = writerDao;
        this.bookDao = bookDao;
    }

    /**
     * Processes a quote event data object by saving the associated quote information.
     *
     * @param bookEventDto The quote event data to be processed.
     *                      This encapsulates information about an event related to a quote.
     *                      It contains details like price and CODE.
     *                      Must not be null.
     */
    @Override
    public void process(BookEventDto bookEventDto) {
        if (bookEventDto == null || bookEventDto.code() == null || bookEventDto.price() == null) {
            String message = "Invalid QuoteEventDto argument";
            logger.info(message);
            return;
        }

        Optional<Writer> writer = this.writerDao.findWriter(bookEventDto.code());
        if (writer.isEmpty()) {
            String message = "Writer not found with the code: '{0}'";
            logger.info(String.format(message, bookEventDto.code()));
            return;
        }
        Book book = EntityMapper.convertToBook(bookEventDto, writer.get());
        this.bookDao.save(book);
    }

}

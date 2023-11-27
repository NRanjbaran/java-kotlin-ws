package service;

import model.dto.WriterEventDto;
import model.entity.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.WriterDao;
import util.EntityMapper;

import java.util.Optional;

/**
 * An implementation of the {@link WriterStreamProcessor} interface that processes
 * writer event data by saving or deleting the writers based on the event type.
 */
public class WriterStreamProcessorImpl implements WriterStreamProcessor {

    private static final Logger logger = LoggerFactory.getLogger(WriterStreamProcessorImpl.class);
    private final WriterDao writerDao;

    /**
     * Constructs a new instance of WriterStreamProcessorImpl with the provided WriterDao.
     *
     * @param writerDao The DAO (Data Access Object) used to interact with the persistence layer
     *                      for storing and retrieving writer data. Must not be null.
     */
    public WriterStreamProcessorImpl(WriterDao writerDao) {
        this.writerDao = writerDao;
    }

    /**
     * Processes an writer event data object by either saving or deleting the associated
     * the writer based on the event type.
     *
     * @param writerEventDto The writer event data to be processed.
     *                           This encapsulates information about an event related to the writer.
     *                           For example, It can receive from a web socket.
     *                           It contains details like CODE, description, WriterEventType, and other relevant information.
     *                           Must not be null.
     */
    @Override
    public void process(WriterEventDto writerEventDto) {
        if (writerEventDto == null || writerEventDto.code() == null || writerEventDto.writerEventType() == null) {
            String message = "Invalid WriterEventDto argument";
            logger.info(message);
            return;
        }

        switch (writerEventDto.writerEventType()) {
            case ADD -> saveWriter(writerEventDto);
            case DELETE -> deleteWriter(writerEventDto);
        }
    }

    /**
     * Saves an writer based on the data in the provided writer event.
     *
     * @param writerEventDto The writer event containing data for the writer to be saved.
     */
    private void saveWriter(WriterEventDto writerEventDto) {
        Writer writer = EntityMapper.convertToWriter(writerEventDto);
        this.writerDao.save(writer);
    }

    /**
     * Deletes an writer based on the CODE provided in the writer event.
     *
     * @param writerEventDto The writer event containing the CODE of the writer to be deleted.
     */
    private void deleteWriter(WriterEventDto writerEventDto) {
        Optional<Writer> writer = this.writerDao.findWriter(writerEventDto.code());
        if (writer.isEmpty()) {
            String message = "Writer not found with the CODE: '{0}'";
            logger.info(String.format(message, writerEventDto.code()));
            return;
        }
        this.writerDao.delete(writer.get());
    }
}

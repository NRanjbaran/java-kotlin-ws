package service;

import model.dto.WriterEventDto;

/**
 * An interface representing a processor for writer event data in a streaming context.
 * Implementations of this interface are responsible for processing writer event data
 * received from a streaming source.
 */
public interface WriterStreamProcessor {

    /**
     * Processes an writer event data object.
     *
     * @param writerEventDto The writer event data to be processed.
     *                           This encapsulates information about an event related to a writer.
     *                           It contains details like CODE, description, and WriterEventType.
     *                           Must not be null.
     */
    void process(WriterEventDto writerEventDto);
}

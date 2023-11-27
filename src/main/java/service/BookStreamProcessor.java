package service;

import model.dto.BookEventDto;

/**
 * An interface representing a processor for quote event data in a streaming context.
 * Implementations of this interface are responsible for processing quote event data
 * received from a streaming source.
 */
public interface BookStreamProcessor {

    /**
     * Processes a quote event data object.
     *
     * @param bookEventDto The quote event data to be processed.
     *                      This encapsulates information about an event related to a quote.
     *                      It contains details like price and CODE.
     *                      Must not be null.
     */
    void process(BookEventDto bookEventDto);
}

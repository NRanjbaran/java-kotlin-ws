package service;

import exception.PublishedInfoManagerException;
import model.dto.WriterInfoDto;

import java.util.List;

/**
 * An interface for managing and retrieving published data for an CODE.
 * Implementations of this interface provide methods to retrieve WriterInfo data based on the provided CODE.
 */
public interface PublishedInfoJavaManager {

    /**
     * Retrieves a list of WriterInfo data with the specified CODE.
     *
     * @param code The Code.
     *             Must not be null or blank.
     * @return A list of {@link WriterInfoDto} objects representing the WriterInfo data.
     * The list may be empty if no data is available for the specified CODE.
     * @throws PublishedInfoManagerException If there is an issue retrieving the WriterInfo data.
     *                                     If the provided CODE is null or blank.
     *                                     This exception is thrown to indicate data retrieval failures.
     */
    List<WriterInfoDto> getWriterInfo(String code)
            throws PublishedInfoManagerException;
}

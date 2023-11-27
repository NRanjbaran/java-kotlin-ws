package service;

import exception.PublishedInfoManagerException;
import model.dto.WriterInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.PublishedInfoRepository;
import repository.WriterDao;

import java.util.List;
import java.util.Optional;

/**
 * An implementation of the {@link PublishedInfoJavaManager} interface that manages and retrieves
 * WriterInfo data.
 */
public class PublishedInfoJavaManagerImpl implements PublishedInfoJavaManager {

    private static final Logger logger = LoggerFactory.getLogger(PublishedInfoJavaManagerImpl.class);
    private static final Double DEFAULT_PRICE = 0d;
    private static final Long VALID_PRICE_DAY = 1L;

    private final PublishedInfoRepository publishedInfoRepository;
    private final WriterDao writerDao;

    /**
     * Constructs a new instance of WriterInfoJavaManagerImpl with the provided WriterInfoRepository.
     *
     * @param publishedInfoRepository The repository used to interact with the persistence layer
     *                                for retrieving WriterInfo data. Must not be null.
     */
    public PublishedInfoJavaManagerImpl(PublishedInfoRepository publishedInfoRepository, WriterDao writerDao) {
        this.publishedInfoRepository = publishedInfoRepository;
        this.writerDao = writerDao;
    }

    /**
     * Retrieves a list of WriterInfo data for a specified CODE and time range.
     * The time range is determined from the present moment to the previous 29 minutes,
     * resulting in a list of 30 records in the WriterInfo list.
     * If received a code for which no Writer exists, an empty list is returned.
     *
     * @param code The CODE.
     *             Must not be null or blank.
     * @return A list of {@link WriterInfoDto} objects representing the WriterInfo data.
     * The list may be empty if no data is available for the specified CODE.
     * @throws PublishedInfoManagerException If there is an issue retrieving the WriterInfo data.
     *                                       If the provided CODE is null or blank.
     *                                       This exception is thrown to indicate data retrieval failures.
     */
    @Override
    public List<WriterInfoDto> getWriterInfo(String code) throws PublishedInfoManagerException {
        validateCODE(code);

        Optional<List<WriterInfoDto>> writerInfos;

        if (!isExistsWriterByCODE(code)) {
            return List.of();
        }

        try {
            writerInfos = publishedInfoRepository.getWriterInfo(code);
        } catch (Exception e) {
            String message = "An error has occurred in getWriterInfoService.";
            logger.error(message.concat(e.getMessage()));
            throw new PublishedInfoManagerException(message);
        }

        return writerInfos.orElse(List.of());
    }

    /**
     * Checks if a writer exists based on the provided CODE.
     *
     * @param code The CODE (International Securities Identification Number) of the writer.
     * @return True if a writer with the given CODE exists, false otherwise.
     */
    private boolean isExistsWriterByCODE(String code) {
        return writerDao
                .findWriter(code)
                .isPresent();
    }

    /**
     * Validates the provided CODE.
     *
     * @param code The CODE to be validated.
     * @throws PublishedInfoManagerException If the provided CODE is null or blank.
     */
    private void validateCODE(String code) throws PublishedInfoManagerException {
        if (code == null || code.isBlank()) {
            throw new PublishedInfoManagerException("Invalid CODE");
        }
    }

}

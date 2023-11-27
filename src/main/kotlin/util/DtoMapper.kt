/**
 * This package contains utility classes for mapping between different model entities and DTOs.
 */
package util

import BookEvent
import WriterEvent
import WriterInfo
import model.dto.BookEventDto
import model.dto.WriterEventDto
import model.dto.WriterEventType
import model.dto.WriterInfoDto

/**
 * A utility class for mapping between model DTOs and entities.
 */
class DtoMapper {

    /**
     * Converts an WriterEvent to an WriterEventDto.
     *
     * @param writerEvent The WriterEvent to be converted.
     * @return The converted WriterEventDto, or null if the input is null.
     * @see WriterEvent
     * @see WriterEventDto
     * @see WriterEventType
     */
    fun convertToWriterDto(writerEventDto: WriterEvent?): WriterEventDto? {
        // TODO
        return if (writerEventDto == null) null else WriterEventDto(
            writerEventDto.data.code,
            writerEventDto.data.description,
            null,
            null,
            null,//getWriterEventType(),
            null
        )
    }

    /**
     * Converts a QuoteEvent to a QuoteEventDto.
     *
     * @param bookEvent The QuoteEvent to be converted.
     * @return The converted QuoteEventDto, or null if the input is null.
     * @see QuoteEvent
     * @see BookEventDto
     */
    fun convertToQuoteDto(bookEvent: BookEvent?): BookEventDto? {
        return if (bookEvent == null) null else BookEventDto(bookEvent.data.price, bookEvent.data.code)
    }

    /**
     * Converts a list of WriterInfoDto instances to a list of writerInfo instances.
     *
     * @param writerInfosDto The list of WriterInfoDto instances to be converted.
     * @return A list of converted writerInfo instances.
     * @see WriterInfoDto
     * @see WriterInfo
     */
    fun convertToWriterInfoList(writerInfo: List<WriterInfoDto>): List<WriterInfo> {
        return writerInfo.map { convertToWriterInfo(it) }
    }

    private fun getWriterEventType(type: WriterEvent.Type): WriterEventType {
        return when (type) {
            WriterEvent.Type.ADD -> WriterEventType.ADD
            WriterEvent.Type.DELETE -> WriterEventType.DELETE
        }
    }

    private fun convertToWriterInfo(writerInfo: WriterInfoDto): WriterInfo {
        return WriterInfo(
            writerInfo.code,
            writerInfo.firstName,
            writerInfo.lastName,
            writerInfo.books
        )
    }

}
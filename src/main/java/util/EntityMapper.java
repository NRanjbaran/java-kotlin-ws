/**
 * This package contains utility classes for the project.
 */
package util;

import model.dto.BookEventDto;
import model.dto.WriterEventDto;
import model.entity.Book;
import model.entity.Writer;

import java.time.Instant;

/**
 * A utility class for mapping between model entities and DTOs.
 */
public class EntityMapper {

    /**
     * Private constructor to prevent instantiation.
     */
    private EntityMapper() {
    }

    /**
     * Converts an {@link WriterEventDto} to an {@link Writer}.
     *
     * @param writerEventDto The WriterEventDto to be converted.
     * @return The converted Writer entity.
     * @see Writer
     * @see WriterEventDto
     */
    public static Writer convertToWriter(WriterEventDto writerEventDto) {
        Writer writer = new Writer();
        writer.setCode(writerEventDto.code());
        writer.setFirstName(writerEventDto.firstName());
        writer.setLastName(writerEventDto.lastName());
        writer.setBooks(writerEventDto.books());
        return writer;
    }

    /**
     * Converts a {@link BookEventDto} and an {@link Writer} to a {@link Book}.
     *
     * @param bookEventDto The QuoteEventDto to be converted.
     * @param writer       The associated Writer entity.
     * @return The converted Quote entity.
     * @see Book
     * @see BookEventDto
     * @see Writer
     */
    public static Book convertToBook(BookEventDto bookEventDto, Writer writer) {
        Book book = new Book();
        book.setWriter(writer);
        book.setPublishedDateTime(Instant.now());
        book.setPrice(bookEventDto.price());
        book.setName(book.getName());
        book.setDescription(book.getDescription());
        return book;
    }
}

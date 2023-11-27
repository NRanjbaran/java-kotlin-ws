package util;

import model.entity.Book;
import model.entity.Writer;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

public class EntityUtil {

    public Writer createDefaultWriter() {
        Writer writer = new Writer();

        Book book1 = new Book();
        book1.setName("Book 1");
        book1.setPrice(1000d);
        book1.setWriter(writer);
        book1.setDescription("Description of book 1");
        Instant publishedDateTime1 = Instant.now().minus(15, ChronoUnit.WEEKS);
        book1.setPublishedDateTime(publishedDateTime1);

        Book book2 = new Book();
        book2.setName("Book 2");
        book2.setPrice(1000d);
        book2.setWriter(writer);
        book2.setDescription("Description of book 2");
        Instant publishedDateTime2 = Instant.now().minus(10, ChronoUnit.WEEKS);
        book1.setPublishedDateTime(publishedDateTime2);

        List<Book> books = List.of(
                book1,
                book2
        );

        String code = UUID.randomUUID().toString();
        writer.setCode(code);
        writer.setBooks(books);
        writer.setFirstName("firstName");
        writer.setLastName("lastName");
        return writer;
    }
}

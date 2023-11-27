/**
 * This package contains DTO (Data Transfer Object) classes that represent data structures for communication.
 */
package model.dto;

import model.entity.Book;

import java.util.List;

public record WriterEventDto(String code, String description, String firstName, String lastName, WriterEventType writerEventType, List<Book> books) {

}

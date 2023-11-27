package model.dto;

import java.util.Set;

public record WriterInfoDto(String code, String firstName, String lastName, Set<BookDto> books) {
}

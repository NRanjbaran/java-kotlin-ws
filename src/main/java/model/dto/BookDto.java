package model.dto;

import java.time.Instant;

public record BookDto(String name, Double price, Instant publishedDateTime, String description) {
}


# java-kotlin-ws

A Java and Kotlin-based application using Hibernate and PostgreSQL to manage writers and books without using Spring Boot.

## Technologies

- Java 17
- Kotlin 1.8
- Hibernate 6
- PostgreSQL 42
- JUnit 5
- Mockito 5
- Gradle

## Introduction

This project demonstrates how to build a web application for managing writers and their books using Java, Kotlin, Hibernate, and PostgreSQL. The application provides a set of REST controllers for adding, deleting, and retrieving writers and their associated books. It also offers a service to find all writers with books published between two specified dates.

## Usage

### REST Controllers (Kotlin)

- **Writer Controller**:
  - Endpoint: `/writers`
  - Functionality: Add or delete a writer.
  - Adding a writer allows you to associate books with them. If the writer doesn't exist for a book, it will be ignored.
  
- **Book Controller**:
  - Endpoint: `/books`
  - Functionality: Add a book.
  - Books are associated with writers and stored accordingly.

### Entity Relationship

- **Writer Entity**: Represents a writer with various properties.
  - Has a many-to-one relationship with the **Book Entity**.
  - Deleting a writer will remove all associated books due to a cascade relationship.

- **Book Entity**: Represents a book with its attributes.

### Service Controller

- **Find Writers with Books Between Dates**:
  - Endpoint: `/writers/books`
  - Functionality: Retrieve all writers along with their books published between two specified dates.

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/your-repo.git
   ```

2. Build and run the application:
   ```bash
   ./gradlew build
   java -jar build/libs/your-application.jar
   ```

## Testing

The project uses JUnit 5 and Mockito for testing. You can run the tests using:
```bash
./gradlew test
```

## Contributing

Contributions are welcome! Please follow the standard GitHub flow (fork the repository, create a branch, make changes, submit a pull request).

## License

This project is licensed under the [MIT License](LICENSE).

/**
 * This package contains classes responsible for data access and repository operations.
 * The repository classes manage interactions with the database and provide access to data entities.
 * The DAO (Data Access Object) classes provide specific data manipulation functionality.
 */
package repository;

import org.hibernate.Session;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * An interface for executing database functions using Hibernate sessions.
 * Implementations of this interface provide methods to execute functionalities
 * for interacting with the database.
 */
public interface HibernateFunctionExecutor {

    /**
     * Executes a database function using a Hibernate session and returns the result.
     *
     * @param function The function to be executed on the Hibernate session.
     * @param <T>      The type of result returned by the function.
     * @return An {@link Optional} containing the result of the executed function.
     * If the function returns null, an empty {@link Optional} is returned.
     * @see Session
     */
    <T> Optional<T> executeFunction(Function<Session, T> function);

    /**
     * Executes a database function using a Hibernate session
     * This method is used for performing operations that don't return a result.
     *
     * @param function The template operation to be executed on the Hibernate session.
     * @see Session
     */
    void executeTemplate(Consumer<Session> function);
}

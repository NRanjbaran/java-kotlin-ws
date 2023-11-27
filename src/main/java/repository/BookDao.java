/**
 * This package contains classes responsible for data access and repository operations.
 * The repository classes manage interactions with the database and provide access to data entities.
 * The DAO (Data Access Object) classes provide specific data manipulation functionality.
 */
package repository;

import model.entity.Book;

import java.util.List;
import java.util.Optional;

/**
 * A data access object (DAO) class for performing database operations related to Quote entities.
 * Extends the {@link BaseDao} class with the type parameter set to {@link Book}.
 */
public class BookDao extends BaseDao<Book> {

    private final HibernateFunctionExecutor hibernateFunctionExecutor;

    /**
     * Constructs a new instance of QuoteDao with the provided DbFunctionExecutor.
     *
     * @param hibernateFunctionExecutor The executor for database functions. Must not be null.
     */
    public BookDao(HibernateFunctionExecutor hibernateFunctionExecutor) {
        super(hibernateFunctionExecutor);
        this.hibernateFunctionExecutor = hibernateFunctionExecutor;
    }

    public Optional<List<Book>> findBooks(String code) {
        return hibernateFunctionExecutor.executeFunction(
                session -> {
                    List<Book> books = session.createQuery(
                                    "select wr from Writer wr join wr.books q where wr.code = :code",
                                    Book.class
                            ).setParameter("code", code)
                            .getResultList();
                    if (books != null && !books.isEmpty()) {
                        return books;
                    }
                    return null;
                });
    }
}

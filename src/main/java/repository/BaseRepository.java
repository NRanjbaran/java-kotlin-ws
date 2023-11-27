/**
 * This package contains classes responsible for data access and repository operations.
 * The repository classes manage interactions with the database and provide access to data entities.
 * The DAO (Data Access Object) classes provide specific data manipulation functionality.
 */
package repository;

/**
 * An abstract class providing common database interaction operations for repositories.
 */
public abstract class BaseRepository {

    private final HibernateFunctionExecutor hibernateFunctionExecutor;

    /**
     * Constructs a new instance of BaseRepository with the provided {@link HibernateFunctionExecutor}.
     *
     * @param hibernateFunctionExecutor The executor for database functions. Must not be null.
     */
    protected BaseRepository(HibernateFunctionExecutor hibernateFunctionExecutor) {
        this.hibernateFunctionExecutor = hibernateFunctionExecutor;
    }
}

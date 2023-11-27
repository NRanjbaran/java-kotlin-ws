/**
 * This package contains classes responsible for data access and repository operations.
 * The repository classes manage interactions with the database and provide access to data entities.
 * The DAO (Data Access Object) classes provide specific data manipulation functionality.
 */
package repository;

import model.entity.BaseEntity;

/**
 * An abstract class providing basic database interaction operations for entities.
 *
 * @param <T> The type of entity being managed, extending {@link BaseEntity}.
 */
public abstract class BaseDao<T extends BaseEntity> {

    private final HibernateFunctionExecutor hibernateFunctionExecutor;

    /**
     * Constructs a new instance of BaseDao with the provided {@link HibernateFunctionExecutor}.
     *
     * @param hibernateFunctionExecutor The executor for database functions. Must not be null.
     */
    protected BaseDao(HibernateFunctionExecutor hibernateFunctionExecutor) {
        this.hibernateFunctionExecutor = hibernateFunctionExecutor;
    }

    /**
     * Saves an entity to the database.
     *
     * @param entity The entity to be saved.
     */
    public void save(T entity) {
        hibernateFunctionExecutor.executeTemplate(session -> session.persist(entity));
    }

}

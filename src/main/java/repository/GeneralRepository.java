/**
 * This package contains classes responsible for data access and repository operations.
 * The repository classes manage interactions with the database and provide access to data entities.
 * The DAO (Data Access Object) classes provide specific data manipulation functionality.
 */
package repository;

import model.entity.BaseEntity;

import java.util.List;

/**
 * A repository class for performing database-related operations, like table truncation.
 * Extends the {@link BaseRepository} class.
 */
public class GeneralRepository extends BaseRepository {

    private final HibernateFunctionExecutor hibernateFunctionExecutor;

    /**
     * Constructs a new instance of DbRepository with the provided DbFunctionExecutor.
     *
     * @param hibernateFunctionExecutor The executor for database functions. Must not be null.
     */
    public GeneralRepository(HibernateFunctionExecutor hibernateFunctionExecutor) {
        super(hibernateFunctionExecutor);
        this.hibernateFunctionExecutor = hibernateFunctionExecutor;
    }

    /**
     * Truncates the specified database tables.
     *
     * @param tables The names of the tables to be truncated.
     */
    public void truncateTables(List<String> tables) {
        hibernateFunctionExecutor.executeTemplate((session -> {
            String tablesAsStatement = String.join(", ", tables);
            String deleteQuery = "truncate table %s";
            session.createNativeQuery(String.format(deleteQuery, tablesAsStatement), BaseEntity.class);
        }));
    }
}

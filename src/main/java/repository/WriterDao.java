/**
 * This package contains classes responsible for data access and repository operations.
 * The repository classes manage interactions with the database and provide access to data entities.
 * The DAO (Data Access Object) classes provide specific data manipulation functionality.
 */
package repository;

import model.entity.Writer;

import java.util.List;
import java.util.Optional;

/**
 * A data access object (DAO) class for performing database operations related to Writer entities.
 * Extends the {@link BaseDao} class with the type parameter set to {@link Writer}.
 */
public class WriterDao extends BaseDao<Writer> {

    private final HibernateFunctionExecutor hibernateFunctionExecutor;

    /**
     * Constructs a new instance of WriterDao with the provided DbFunctionExecutor.
     *
     * @param hibernateFunctionExecutor The executor for database functions. Must not be null.
     */
    public WriterDao(HibernateFunctionExecutor hibernateFunctionExecutor) {
        super(hibernateFunctionExecutor);
        this.hibernateFunctionExecutor = hibernateFunctionExecutor;
    }

    /**
     * Finds a writer by its code.
     *
     * @param code The unique code of the writer to find.
     * @return An {@link Optional} containing the found writer, if available. Otherwise, an empty {@link Optional} is returned.
     */
    public Optional<Writer> findWriter(String code) {
        return hibernateFunctionExecutor.executeFunction(
                session -> {
                    List<Writer> writers = session.createQuery(
                                    "select wr from Writer wr where wr.code = :code",
                                    Writer.class
                            ).setParameter("code", code)
                            .getResultList();
                    if (writers != null && !writers.isEmpty()) {
                        return writers.get(0);
                    }
                    return null;
                });
    }

    /**
     * Deletes a writer from the database.
     *
     * @param writer The writer entity to be deleted.
     */
    public void delete(Writer writer) {
        hibernateFunctionExecutor.executeTemplate(session -> {
            Writer foundWriter = session.find(Writer.class, writer.getId());
            session.remove(foundWriter);
        });
    }
}

/**
 * This package contains classes responsible for data access and repository operations.
 * The repository classes manage interactions with the database and provide access to data entities.
 * The DAO (Data Access Object) classes provide specific data manipulation functionality.
 */
package repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * An implementation of {@link HibernateFunctionExecutor} using Hibernate sessions to execute database functions and templates.
 */
public class HibernateFunctionExecutorImpl implements HibernateFunctionExecutor {

    /**
     * Executes a database function using a Hibernate session and returns the result.
     *
     * @param function The function to be executed on the Hibernate session.
     * @param <T>      The type of result returned by the function.
     * @return An {@link Optional} containing the result of the executed function.
     * If the function returns null, an empty {@link Optional} is returned.
     * @see Session
     * @see Transaction
     * @see Function
     */
    @Override
    public <T> Optional<T> executeFunction(Function<Session, T> function) {
        T result = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            result = function.apply(session);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
        return Optional.ofNullable(result);
    }

    /**
     * Executes a database template consumer function using a Hibernate session.
     * This method is used for performing operations that don't return a result.
     *
     * @param function The template operation to be executed on the Hibernate session.
     * @see Session
     * @see Transaction
     * @see Consumer
     */
    @Override
    public void executeTemplate(Consumer<Session> function) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            function.accept(session);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    public static HibernateFunctionExecutorImpl newInstance() {
        return new HibernateFunctionExecutorImpl();
    }
}

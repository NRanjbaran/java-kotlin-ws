/**
 * This package contains utility classes for the project.
 */
package util;

import model.entity.Writer;
import model.entity.Book;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * A utility singleton class for managing the Hibernate SessionFactory instance.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    /**
     * Private constructor to prevent instantiation.
     */
    private HibernateUtil() {
    }

    /**
     * Gets the Hibernate SessionFactory instance.
     *
     * @return The Hibernate SessionFactory instance.
     * @see SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.configure("hibernate.cfg.xml");
                configuration.addAnnotatedClass(Writer.class);
                configuration.addAnnotatedClass(Book.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

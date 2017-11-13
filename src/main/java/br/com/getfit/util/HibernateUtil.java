package br.com.getfit.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Francisco
 */
@Transactional
public class HibernateUtil {

    private static final EntityManagerFactory emf;
    private static EntityManager entityManager = null;
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            emf = Persistence.createEntityManagerFactory("getfitPU");
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }
}

package br.com.getfit.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

/**
 * Utilitário JPA para fácil acesso ao EntityManager
 *
 * @author Francisco
 */
@Transactional
public class JPAUtil {

    private static final EntityManagerFactory emf;
    private static EntityManager entityManager = null;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("getfitPU", PersistencePropertiesUtil.get());
        } catch (Throwable ex) {
            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }
}

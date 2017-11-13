package br.com.getfit.dao;

import br.com.getfit.util.HibernateUtil;
import java.io.Serializable;
import javax.persistence.TypedQuery;

/**
 *
 * @author Francisco
 */
public class DAOBase<T> implements Serializable {

    private Class<T> entityClass;

    public TypedQuery<T> createNamedQuery(String query) {
        return HibernateUtil.getEntityManager().createNamedQuery(query, entityClass);
    }
}

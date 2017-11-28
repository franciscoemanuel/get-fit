package br.com.getfit.dao;

import br.com.getfit.util.JPAUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author Francisco
 */
public abstract class AbstractDAO<T> implements Serializable {

    private Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void salvar(T entity) throws ConstraintViolationException {
        try {
            JPAUtil.getEntityManager().getTransaction().begin();
            JPAUtil.getEntityManager().persist(entity);
            JPAUtil.getEntityManager().getTransaction().commit();
            JPAUtil.getEntityManager().close();
        } catch (PersistenceException e) {
            e.printStackTrace();
            JPAUtil.getEntityManager().getTransaction().rollback();
            JPAUtil.getEntityManager().close();
        }
    }

    public void remover(Object id) {
        try {
            JPAUtil.getEntityManager().getTransaction().begin();
            JPAUtil.getEntityManager().remove(JPAUtil.getEntityManager().getReference(entityClass, id));
            JPAUtil.getEntityManager().getTransaction().commit();
            JPAUtil.getEntityManager().close();
        } catch (Exception e) {
            JPAUtil.getEntityManager().getTransaction().rollback();
            JPAUtil.getEntityManager().close();
        }
    }

    public T atualizar(T entity) throws ConstraintViolationException {
        T entidadeAtualizada = null;
        try {
            JPAUtil.getEntityManager().getTransaction().begin();
            entidadeAtualizada = JPAUtil.getEntityManager().merge(entity);
            JPAUtil.getEntityManager().getTransaction().commit();
            JPAUtil.getEntityManager().close();
        } catch (PersistenceException e) {
            JPAUtil.getEntityManager().getTransaction().rollback();
            JPAUtil.getEntityManager().close();
        }
        return entidadeAtualizada;
    }

    public T buscarPorId(int entityId) {
        return JPAUtil.getEntityManager().find(entityClass, entityId);
    }

    public T findReferenceOnly(int entityID) {
        return JPAUtil.getEntityManager().getReference(entityClass, entityID);
    }

    public List<T> buscarTodos() {
        CriteriaQuery<T> cq = (CriteriaQuery<T>) JPAUtil.getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return JPAUtil.getEntityManager().createQuery(cq).getResultList();
    }

    public TypedQuery<T> criaNamedQuery(String query) {
        return JPAUtil.getEntityManager().createNamedQuery(query, entityClass);
    }

    public Query criaQueryNativa(String query) {
        return JPAUtil.getEntityManager().createNativeQuery(query, entityClass);
    }

}

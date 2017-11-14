package br.com.getfit.dao;

import br.com.getfit.util.JPAUtil;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Francisco
 */
@Transactional
public abstract class AbstractDAO<T> implements Serializable {

    private Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void salvar(T entity) {
        try {
            JPAUtil.getEntityManager().getTransaction().begin();
            JPAUtil.getEntityManager().persist(entity);
            JPAUtil.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void excluir(Object id) {
        try {
            JPAUtil.getEntityManager().getTransaction().begin();
            JPAUtil.getEntityManager().remove(JPAUtil.getEntityManager().getReference(entityClass, id));
            JPAUtil.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public T atualizar(T entity) {
        JPAUtil.getEntityManager().getTransaction().begin();
        T entidadeAtualizada = JPAUtil.getEntityManager().merge(entity);
        JPAUtil.getEntityManager().getTransaction().commit();
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

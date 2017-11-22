package br.com.getfit.validation;

import br.com.getfit.util.JPAUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.ocpsoft.shade.org.apache.commons.beanutils.PropertyUtils;

/**
 *
 * @author Francisco
 */
public class UniqueValidator implements ConstraintValidator<Unique, Serializable> {

    private String[] columnNames;

    @Override
    public void initialize(Unique uniqueAnnotation) {
        columnNames = uniqueAnnotation.columnNames();
    }

    @Override
    public boolean isValid(Serializable target, ConstraintValidatorContext context) {
        Boolean valid = true;
        if (target == null) {
            return true;
        }
        try {
            System.out.println(target.getClass());
            Class<?> entityClass = target.getClass();
            String colunaId = JPAUtil.getIdAttribute(entityClass).getName();
            Object valorId = PropertyUtils.getProperty(target, colunaId);

            JPAUtil.getEntityManager().clear();

            CriteriaBuilder criteriaBuilder = JPAUtil.getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
            Root<?> root = criteriaQuery.from(entityClass);

            SingularAttribute idAttribute = JPAUtil.getIdAttribute(entityClass);
            Path<?> pathToId = root.get(idAttribute);
            
            List<Predicate> predicates = new ArrayList<Predicate>(columnNames.length);
            
            for (String columnName : columnNames) {
                Object valorUnico = PropertyUtils.getProperty(target, columnName);
                Predicate buscaValor = criteriaBuilder.equal(root.get(columnName), valorUnico);
                Predicate diferenteDoId = criteriaBuilder.notEqual(root.get(colunaId), valorId);
                Predicate predicate = criteriaBuilder.and(buscaValor, diferenteDoId);
                predicates.add(predicate);
            }

            CriteriaQuery<Object> select = criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
            valid = JPAUtil.getEntityManager().createQuery(select).getResultList().isEmpty();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao validar unique contraint" + e.getMessage());
        }
        return valid;
    }
}

package br.com.getfit.validator;

import br.com.getfit.util.JPAUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.hibernate.Session;
import static org.hibernate.annotations.common.util.StringHelper.root;

/**
 *
 * @author Francisco
 */
public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    private String columnName;
    private Class<?> entityClass;

    @Override
    public void initialize(Unique uniqueAnnotation) {
        columnName = uniqueAnnotation.columnName();
        entityClass = uniqueAnnotation.entityClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Boolean valid = true;
        if (value == null) {
            return true;
        }
        try {
            JPAUtil.getEntityManager().clear();
            CriteriaBuilder criteriaBuilder = JPAUtil.getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
            Root<?> root = criteriaQuery.from(entityClass);
            SingularAttribute idAttribute = JPAUtil.getIdAttribute(entityClass);
            Path<?> pathToId = root.get(idAttribute);
            Predicate predicate = criteriaBuilder.equal(root.get(columnName), value);
            CriteriaQuery<Object> select = criteriaQuery.select(root).where(predicate);
            valid = JPAUtil.getEntityManager().createQuery(select).getResultList().isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao validar unique contraint" + e.getMessage());
        }
        return valid;
    }
}

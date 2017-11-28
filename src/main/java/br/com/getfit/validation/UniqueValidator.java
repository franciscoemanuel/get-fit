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

    private String columnName;

    @Override
    public void initialize(Unique uniqueAnnotation) {
        columnName = uniqueAnnotation.columnName();
    }

    @Override
    public boolean isValid(Serializable target, ConstraintValidatorContext context) {
        Boolean valid = true;
        if (target == null) {
            return true;
        }
        try {
            Predicate busca = null;
            Class<?> entityClass = target.getClass();
            String colunaId = JPAUtil.getIdAttribute(entityClass).getName();
            Object valorId = PropertyUtils.getProperty(target, colunaId);

            CriteriaBuilder criteriaBuilder = JPAUtil.getEntityManager().getCriteriaBuilder();
            CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
            Root<?> root = criteriaQuery.from(entityClass);

            SingularAttribute idAttribute = JPAUtil.getIdAttribute(entityClass);
            Path<?> pathToId = root.get(idAttribute);

            Object valorUnico = PropertyUtils.getProperty(target, columnName);

            if (valorId == null) {
                busca = criteriaBuilder.equal(root.get(columnName), valorUnico);
                JPAUtil.getEntityManager().clear();
            } else {
                JPAUtil.getEntityManager().close();
                Predicate diferenteDoId = criteriaBuilder.notEqual(root.get(colunaId), valorId);
                Predicate buscaValor = criteriaBuilder.equal(root.get(columnName), valorUnico);
                Predicate predicate = criteriaBuilder.and(buscaValor, diferenteDoId);
            }

            CriteriaQuery<Object> select = criteriaQuery.select(root).where(busca);
            valid = JPAUtil.getEntityManager().createQuery(select).getResultList().isEmpty();

            if (!valid) {
                criaMensagensErro(columnName, context);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao validar unique contraint" + e.getMessage());
        }
        return valid;
    }

    private void criaMensagensErro(String columnNames, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addPropertyNode(columnName).addConstraintViolation();
    }
}

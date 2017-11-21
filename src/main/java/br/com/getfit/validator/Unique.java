package br.com.getfit.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Francisco
 */
@Target({FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
@Documented
public @interface Unique {

    String message() default "{O valor inserido já existe no banco de dados}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String columnName();

    Class<?> entityClass();

    @Target({FIELD, METHOD, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        Unique[] value();
    }

}

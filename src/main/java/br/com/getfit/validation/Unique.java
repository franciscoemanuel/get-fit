package br.com.getfit.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Francisco
 */
@Target({ElementType.TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
@Documented
public @interface Unique {

    String message() default "{O valor inserido já existe no banco de dados}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] columnNames();
    
    @Target({ElementType.TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        Unique[] value();
    }

}

package pl.kurs.validation.annotation;

import pl.kurs.validation.impl.MatchingParametersValidator;
import pl.kurs.validation.impl.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MatchingParametersValidator.class)
public @interface MatchingParameters {
    String message() default "PARAMETERS_NOT_MATCH_FIGURE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

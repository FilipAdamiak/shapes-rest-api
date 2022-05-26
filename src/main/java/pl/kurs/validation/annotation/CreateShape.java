package pl.kurs.validation.annotation;

import pl.kurs.validation.impl.CreateShapeValidator;
import pl.kurs.validation.impl.MatchingParametersValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CreateShapeValidator.class)
public @interface CreateShape {
    String message() default "UNMATCHED_PARAMETERS";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

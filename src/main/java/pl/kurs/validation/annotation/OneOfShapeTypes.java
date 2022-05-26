package pl.kurs.validation.annotation;

import pl.kurs.validation.impl.OneOfShapeTypesValidator;
import pl.kurs.validation.impl.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OneOfShapeTypesValidator.class)
public @interface OneOfShapeTypes {

    String message() default "INCORRECT_SHAPE_TYPE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

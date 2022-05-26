package pl.kurs.validation.impl;

import org.springframework.stereotype.Service;
import pl.kurs.model.enums.Type;
import pl.kurs.validation.annotation.OneOfShapeTypes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.EnumSet;
import java.util.Set;

@Service
public class OneOfShapeTypesValidator implements ConstraintValidator<OneOfShapeTypes, String> {

    private final Set<String> types = Set.copyOf(Type.getLabels());

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return types.contains(s);
    }

}

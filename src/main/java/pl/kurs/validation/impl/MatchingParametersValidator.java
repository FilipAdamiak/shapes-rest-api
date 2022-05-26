package pl.kurs.validation.impl;


import org.springframework.stereotype.Service;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.model.enums.Type;
import pl.kurs.validation.annotation.MatchingParameters;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Service
public class MatchingParametersValidator implements ConstraintValidator<MatchingParameters, SearchShapeCommand> {

    @Override
    public void initialize(MatchingParameters constraintAnnotation) {

    }

    @Override
    public boolean isValid(SearchShapeCommand command, ConstraintValidatorContext constraintValidatorContext) {
        String type = command.getType();
        boolean flag = false;
        if (Objects.equals(type, Type.CIRCLE.label)) {
            flag = isCorrect(command.getParameter().getRadiusFrom()) || isCorrect(command.getParameter().getRadiusTo());
        } else if (Objects.equals(type, Type.RECTANGLE.label)) {
            flag = isCorrect(command.getParameter().getHeightFrom()) || isCorrect(command.getParameter().getHeightTo())
                    || isCorrect(command.getParameter().getWidthFrom()) || isCorrect(command.getParameter().getWidthTo());
        } else if (Objects.equals(type, Type.PENTAGON.label)) {
            flag = isCorrect(command.getParameter().getSideFrom()) || isCorrect(command.getParameter().getSideTo());
        } else if (Objects.equals(type, Type.SQUARE.label)) {
            flag = isCorrect(command.getParameter().getSideFrom()) || isCorrect(command.getParameter().getSideTo());
        } else if (Objects.equals(type, Type.TRAPEZOID.label)) {
            flag = isCorrect(command.getParameter().getBottomBaseFrom()) || isCorrect(command.getParameter().getBottomBaseTo())
                    || isCorrect(command.getParameter().getUpperBaseFrom()) || isCorrect(command.getParameter().getUpperBaseTo()) ||
                    isCorrect(command.getParameter().getHeightFrom()) || isCorrect(command.getParameter().getHeightTo());
        } else if (Objects.equals(type, Type.TRIANGLE.label)) {
            flag = isCorrect(command.getParameter().getBottomBaseFrom()) || isCorrect(command.getParameter().getBottomBaseTo()) ||
                    isCorrect(command.getParameter().getHeightFrom()) || isCorrect(command.getParameter().getHeightTo());
        }
        return flag;
    }

    public boolean isCorrect(Double param) {
        return (param != null) && (!param.isNaN());
    }

}

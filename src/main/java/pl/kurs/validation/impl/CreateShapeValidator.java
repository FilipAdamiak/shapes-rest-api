package pl.kurs.validation.impl;

import org.springframework.stereotype.Service;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.enums.Type;
import pl.kurs.validation.annotation.CreateShape;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Service
public class CreateShapeValidator implements ConstraintValidator<CreateShape, CreateShapeCommand> {


    @Override
    public boolean isValid(CreateShapeCommand command, ConstraintValidatorContext constraintValidatorContext) {
        String type = command.getType();
        boolean flag = false;
        if(Objects.equals(type, Type.CIRCLE.label)) {
            flag = isCorrect(command.getParameter().getRadius());
        } else if(Objects.equals(type, Type.RECTANGLE.label)) {
            flag = isCorrect(command.getParameter().getHeight()) || isCorrect(command.getParameter().getWidth());
        } else if(Objects.equals(type, Type.PENTAGON.label)) {
            flag = isCorrect(command.getParameter().getSide());
        } else if(Objects.equals(type, Type.SQUARE.label)) {
            flag = isCorrect(command.getParameter().getSide());
        } else if(Objects.equals(type, Type.TRAPEZOID.label)) {
            flag = isCorrect(command.getParameter().getBottomBase()) || isCorrect(command.getParameter().getHeight())
                    || isCorrect(command.getParameter().getUpperBase());
        } else if(Objects.equals(type, Type.TRIANGLE.label)) {
            flag = isCorrect(command.getParameter().getBottomBase()) || isCorrect(command.getParameter().getHeight());
        }
        return flag;
    }

    public boolean isCorrect(Double param) {
        return (param != null) && (!param.isNaN());
    }

}

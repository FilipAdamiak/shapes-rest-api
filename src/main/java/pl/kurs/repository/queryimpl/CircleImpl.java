package pl.kurs.repository.queryimpl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.model.enums.Type;
import pl.kurs.model.figures.Circle;
import pl.kurs.model.figures.QCircle;
import pl.kurs.repository.queryinterface.ShapeInterface;

import java.util.Optional;

@Service
public class CircleImpl implements ShapeInterface<Circle> {

    @Override
    public String getType() {
        return Type.CIRCLE.label;
    }

    @Override
    public Circle mapCommandToEntity(CreateShapeCommand command) {
        return Circle.builder()
                .createdDate(command.getDate())
                .radius(command.getParameter().getRadius())
                .createdBy(command.getUser())
                .build();
    }

    @Override
    public Predicate toPredicate(SearchShapeCommand parameters) {
        BooleanBuilder builder = new BooleanBuilder();
        QCircle qCircle = new QCircle("shape");
        Optional.ofNullable(parameters.getParameter().getRadiusFrom())
                .map(qCircle.radius::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getRadiusTo())
                .map(qCircle.radius::loe).ifPresent(builder::and);
        Optional.of(parameters.getParameter().getUserEmail())
                .map(qCircle.createdBy.email::eq).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedFrom())
                .map(qCircle.createdDate::after).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedTo())
                .map(qCircle.createdDate::before).ifPresent(builder::and);
        return builder;
    }

}

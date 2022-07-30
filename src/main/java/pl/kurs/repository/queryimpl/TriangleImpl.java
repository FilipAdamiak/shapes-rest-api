package pl.kurs.repository.queryimpl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.model.enums.Type;
import pl.kurs.model.figures.QTriangle;
import pl.kurs.model.figures.Triangle;
import pl.kurs.repository.queryinterface.ShapeInterface;

import java.util.Optional;

@Service
public class TriangleImpl implements ShapeInterface<Triangle> {

    @Override
    public String getType() {
        return Type.TRIANGLE.label;
    }

    @Override
    public Triangle mapCommandToEntity(CreateShapeCommand command) {
        return Triangle.builder()
                .createdDate(command.getDate())
                .base(command.getParameter().getBase())
                .height(command.getParameter().getHeight())
                .createdBy(command.getUser())
                .build();
    }

    @Override
    public Predicate toPredicate(SearchShapeCommand parameters) {
        BooleanBuilder builder = new BooleanBuilder();
        QTriangle qTriangle = new QTriangle("shape");
        Optional.ofNullable(parameters.getParameter().getBottomBaseFrom())
                .map(qTriangle.base::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getBottomBaseTo())
                .map(qTriangle.base::loe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getHeightFrom())
                .map(qTriangle.height::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getHeightTo())
                .map(qTriangle.height::loe).ifPresent(builder::and);
        Optional.of(parameters.getParameter().getUserEmail())
                .map(qTriangle.createdBy.email::eq).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedFrom())
                .map(qTriangle.createdDate::after).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedTo())
                .map(qTriangle.createdDate::before).ifPresent(builder::and);
        return builder;
    }

}
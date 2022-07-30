package pl.kurs.repository.queryimpl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.model.enums.Type;
import pl.kurs.model.figures.QRectangle;
import pl.kurs.model.figures.Rectangle;
import pl.kurs.repository.queryinterface.ShapeInterface;

import java.util.Optional;

@Service
public class RectangleImpl implements ShapeInterface<Rectangle> {


    @Override
    public String getType() {
        return Type.RECTANGLE.label;
    }

    @Override
    public Rectangle mapCommandToEntity(CreateShapeCommand command) {
        return Rectangle.builder()
                .createdDate(command.getDate())
                .width(command.getParameter().getWidth())
                .height(command.getParameter().getHeight())
                .createdBy(command.getUser())
                .build();
    }

    @Override
    public Predicate toPredicate(SearchShapeCommand parameters) {
        BooleanBuilder builder = new BooleanBuilder();
        QRectangle qRectangle = new QRectangle("shape");
        Optional.ofNullable(parameters.getParameter().getHeightFrom())
                .map(qRectangle.height::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getHeightTo())
                .map(qRectangle.height::loe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getWidthFrom())
                .map(qRectangle.width::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getWidthTo())
                .map(qRectangle.width::loe).ifPresent(builder::and);
        Optional.of(parameters.getParameter().getUserEmail())
                .map(qRectangle.createdBy.email::eq).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedFrom())
                .map(qRectangle.createdDate::after).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedTo())
                .map(qRectangle.createdDate::before).ifPresent(builder::and);
        return builder;
    }
}

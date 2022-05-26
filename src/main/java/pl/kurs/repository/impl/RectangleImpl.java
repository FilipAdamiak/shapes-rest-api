package pl.kurs.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.model.enums.Type;
import pl.kurs.model.figures.QRectangle;
import pl.kurs.model.figures.Rectangle;
import pl.kurs.repository.RectangleRepository;
import pl.kurs.repository.repointerface.ShapeInterface;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RectangleImpl implements ShapeInterface<Rectangle> {

    private final RectangleRepository repo;

    @Override
    public String getType() {
        return Type.RECTANGLE.label;
    }

    @Override
    public Page<Rectangle> findAllShapes(Predicate predicate, Pageable pageable) {
        return repo.findAll(predicate, pageable);
    }

    @Override
    public Predicate toPredicate(SearchShapeCommand parameters) {
        BooleanBuilder builder = new BooleanBuilder();
        Optional.ofNullable(parameters.getParameter().getHeightFrom())
                .map(QRectangle.rectangle.height::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getHeightTo())
                .map(QRectangle.rectangle.height::loe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getWidthFrom())
                .map(QRectangle.rectangle.width::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getWidthTo())
                .map(QRectangle.rectangle.width::loe).ifPresent(builder::and);
        Optional.of(parameters.getParameter().getUserEmail())
                .map(QRectangle.rectangle.createdBy.email::eq).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedFrom())
                .map(QRectangle.rectangle.createdDate::after).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedTo())
                .map(QRectangle.rectangle.createdDate::before).ifPresent(builder::and);
        return builder;
    }
}

package pl.kurs.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.model.enums.Type;
import pl.kurs.model.figures.QTriangle;
import pl.kurs.model.figures.Triangle;
import pl.kurs.repository.TriangleRepository;
import pl.kurs.repository.repointerface.ShapeInterface;
import pl.kurs.repository.ShapeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TriangleImpl implements ShapeInterface<Triangle> {

    private final TriangleRepository repo;

    @Override
    public String getType() {
        return Type.TRIANGLE.label;
    }

    @Override
    public Page<Triangle> findAllShapes(Predicate predicate, Pageable pageable) {
        return repo.findAll(predicate, pageable);
    }

    @Override
    public Predicate toPredicate(SearchShapeCommand parameters) {
        BooleanBuilder builder = new BooleanBuilder();
        Optional.ofNullable(parameters.getParameter().getBottomBaseFrom())
                .map(QTriangle.triangle.base::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getBottomBaseTo())
                .map(QTriangle.triangle.base::loe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getHeightFrom())
                .map(QTriangle.triangle.height::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getHeightTo())
                .map(QTriangle.triangle.height::loe).ifPresent(builder::and);
        Optional.of(parameters.getParameter().getUserEmail())
                .map(QTriangle.triangle.createdBy.email::eq).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedFrom())
                .map(QTriangle.triangle.createdDate::after).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedTo())
                .map(QTriangle.triangle.createdDate::before).ifPresent(builder::and);
        return builder;
    }

}
package pl.kurs.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.model.enums.Type;
import pl.kurs.model.figures.QTrapezoid;
import pl.kurs.model.figures.Trapezoid;
import pl.kurs.repository.TrapezoidRepository;
import pl.kurs.repository.repointerface.ShapeInterface;
import pl.kurs.repository.ShapeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrapezoidImpl implements ShapeInterface<Trapezoid> {

    private final TrapezoidRepository repo;

    @Override
    public String getType() {
        return Type.TRAPEZOID.label;
    }

    @Override
    public Page<Trapezoid> findAllShapes(Predicate predicate, Pageable pageable) {
        return repo.findAll(predicate, pageable);
    }

    @Override
    public Predicate toPredicate(SearchShapeCommand parameters) {
        BooleanBuilder builder = new BooleanBuilder();
        Optional.ofNullable(parameters.getParameter().getBottomBaseFrom())
                .map(QTrapezoid.trapezoid.bottomBase::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getBottomBaseTo())
                .map(QTrapezoid.trapezoid.bottomBase::loe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getUpperBaseFrom())
                .map(QTrapezoid.trapezoid.upperBase::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getUpperBaseTo())
                .map(QTrapezoid.trapezoid.upperBase::loe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getHeightFrom())
                .map(QTrapezoid.trapezoid.height::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getHeightTo())
                .map(QTrapezoid.trapezoid.height::loe).ifPresent(builder::and);
        Optional.of(parameters.getParameter().getUserEmail())
                .map(QTrapezoid.trapezoid.createdBy.email::eq).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedFrom())
                .map(QTrapezoid.trapezoid.createdDate::after).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedTo())
                .map(QTrapezoid.trapezoid.createdDate::before).ifPresent(builder::and);
        return builder;
    }
}

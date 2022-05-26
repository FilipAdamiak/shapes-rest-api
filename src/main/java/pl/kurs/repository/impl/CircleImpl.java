package pl.kurs.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.model.enums.Type;
import pl.kurs.model.figures.Circle;
import pl.kurs.model.figures.QCircle;
import pl.kurs.repository.CircleRepository;
import pl.kurs.repository.repointerface.ShapeInterface;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CircleImpl implements ShapeInterface<Circle> {

    private final CircleRepository repo;

    @Override
    public String getType() {
        return Type.CIRCLE.label;
    }

    @Override
    public Page<Circle> findAllShapes(Predicate predicate, Pageable pageable) {
        return repo.findAll(predicate, pageable);
    }

    @Override
    public Predicate toPredicate(SearchShapeCommand parameters) {
        BooleanBuilder builder = new BooleanBuilder();
        Optional.ofNullable(parameters.getParameter().getRadiusFrom())
                .map(QCircle.circle.radius::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getRadiusTo())
                .map(QCircle.circle.radius::loe).ifPresent(builder::and);
        Optional.of(parameters.getParameter().getUserEmail())
                .map(QCircle.circle.createdBy.email::eq).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedFrom())
                .map(QCircle.circle.createdDate::after).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedTo())
                .map(QCircle.circle.createdDate::before).ifPresent(builder::and);
        return builder;
    }
}

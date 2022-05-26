package pl.kurs.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.model.enums.Type;
import pl.kurs.model.figures.QSquare;
import pl.kurs.model.figures.Square;
import pl.kurs.repository.SquareRepository;
import pl.kurs.repository.repointerface.ShapeInterface;
import pl.kurs.repository.ShapeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SquareImpl implements ShapeInterface<Square> {

    private final SquareRepository repo;

    @Override
    public String getType() {
        return Type.SQUARE.label;
    }

    @Override
    public Page<Square> findAllShapes(Predicate predicate, Pageable pageable) {
        return repo.findAll(predicate, pageable);
    }

    @Override
    public Predicate toPredicate(SearchShapeCommand parameters) {
        BooleanBuilder builder = new BooleanBuilder();
        Optional.ofNullable(parameters.getParameter().getSideFrom())
                .map(QSquare.square.side::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getSideTo())
                .map(QSquare.square.side::loe).ifPresent(builder::and);
        Optional.of(parameters.getParameter().getUserEmail())
                .map(QSquare.square.createdBy.email::eq).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedFrom())
                .map(QSquare.square.createdDate::after).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedTo())
                .map(QSquare.square.createdDate::before).ifPresent(builder::and);
        return builder;
    }
}
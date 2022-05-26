package pl.kurs.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.model.enums.Type;
import pl.kurs.model.figures.Pentagon;
import pl.kurs.model.figures.QPentagon;
import pl.kurs.repository.PentagonRepository;
import pl.kurs.repository.repointerface.ShapeInterface;
import pl.kurs.repository.ShapeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PentagonImpl implements ShapeInterface<Pentagon> {

    private final PentagonRepository repo;

    @Override
    public String getType() {
        return Type.PENTAGON.label;
    }

    @Override
    public Page<Pentagon> findAllShapes(Predicate predicate, Pageable pageable) {
        return repo.findAll(predicate, pageable);
    }

    @Override
    public Predicate toPredicate(SearchShapeCommand parameters) {
        BooleanBuilder builder = new BooleanBuilder();
        Optional.ofNullable(parameters.getParameter().getSideFrom())
                .map(QPentagon.pentagon.side::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getSideTo())
                .map(QPentagon.pentagon.side::loe).ifPresent(builder::and);
        Optional.of(parameters.getParameter().getUserEmail())
                .map(QPentagon.pentagon.createdBy.email::eq).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedFrom())
                .map(QPentagon.pentagon.createdDate::after).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedTo())
                .map(QPentagon.pentagon.createdDate::before).ifPresent(builder::and);
        return builder;
    }

}
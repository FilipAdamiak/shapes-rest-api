package pl.kurs.repository.queryimpl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.model.enums.Type;
import pl.kurs.model.figures.QSquare;
import pl.kurs.model.figures.Square;
import pl.kurs.repository.queryinterface.ShapeInterface;

import java.util.Optional;

@Service
public class SquareImpl implements ShapeInterface<Square> {

    @Override
    public String getType() {
        return Type.SQUARE.label;
    }

    @Override
    public Square mapCommandToEntity(CreateShapeCommand command) {
        return Square.builder()
                .createdDate(command.getDate())
                .side(command.getParameter().getSide())
                .createdBy(command.getUser())
                .build();
    }

    @Override
    public Predicate toPredicate(SearchShapeCommand parameters) {
        BooleanBuilder builder = new BooleanBuilder();
        QSquare qSquare = new QSquare("shape");
        Optional.ofNullable(parameters.getParameter().getSideFrom())
                .map(qSquare.side::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getSideTo())
                .map(qSquare.side::loe).ifPresent(builder::and);
        Optional.of(parameters.getParameter().getUserEmail())
                .map(qSquare.createdBy.email::eq).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedFrom())
                .map(qSquare.createdDate::after).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedTo())
                .map(qSquare.createdDate::before).ifPresent(builder::and);
        return builder;
    }
}
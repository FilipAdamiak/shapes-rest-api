package pl.kurs.repository.queryimpl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.model.enums.Type;
import pl.kurs.model.figures.QTrapezoid;
import pl.kurs.model.figures.Trapezoid;
import pl.kurs.repository.queryinterface.ShapeInterface;

import java.util.Optional;

@Service
public class TrapezoidImpl implements ShapeInterface<Trapezoid> {

    @Override
    public String getType() {
        return Type.TRAPEZOID.label;
    }

    @Override
    public Trapezoid mapCommandToEntity(CreateShapeCommand command) {
        return Trapezoid.builder()
                .createdDate(command.getDate())
                .height(command.getParameter().getHeight())
                .bottomBase(command.getParameter().getBottomBase())
                .upperBase(command.getParameter().getUpperBase())
                .createdBy(command.getUser())
                .build();
    }

    @Override
    public Predicate toPredicate(SearchShapeCommand parameters) {
        BooleanBuilder builder = new BooleanBuilder();
        QTrapezoid qTrapezoid = new QTrapezoid("shape");
        Optional.ofNullable(parameters.getParameter().getBottomBaseFrom())
                .map(qTrapezoid.bottomBase::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getBottomBaseTo())
                .map(qTrapezoid.bottomBase::loe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getUpperBaseFrom())
                .map(qTrapezoid.upperBase::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getUpperBaseTo())
                .map(qTrapezoid.upperBase::loe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getHeightFrom())
                .map(qTrapezoid.height::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getHeightTo())
                .map(qTrapezoid.height::loe).ifPresent(builder::and);
        Optional.of(parameters.getParameter().getUserEmail())
                .map(qTrapezoid.createdBy.email::eq).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedFrom())
                .map(qTrapezoid.createdDate::after).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedTo())
                .map(qTrapezoid.createdDate::before).ifPresent(builder::and);
        return builder;
    }
}

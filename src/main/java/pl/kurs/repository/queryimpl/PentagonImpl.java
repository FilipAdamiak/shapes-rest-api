package pl.kurs.repository.queryimpl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Service;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.model.enums.Type;
import pl.kurs.model.figures.Pentagon;
import pl.kurs.model.figures.QPentagon;
import pl.kurs.repository.queryinterface.ShapeInterface;

import java.util.Optional;

@Service
public class PentagonImpl implements ShapeInterface<Pentagon> {

    @Override
    public String getType() {
        return Type.PENTAGON.label;
    }

    @Override
    public Pentagon mapCommandToEntity(CreateShapeCommand command) {
        return Pentagon.builder()
                .createdDate(command.getDate())
                .side(command.getParameter().getSide())
                .createdBy(command.getUser())
                .build();
    }

    @Override
    public Predicate toPredicate(SearchShapeCommand parameters) {
        BooleanBuilder builder = new BooleanBuilder();
        QPentagon qPentagon = new QPentagon("shape");
        Optional.ofNullable(parameters.getParameter().getSideFrom())
                .map(qPentagon.side::goe).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getSideTo())
                .map(qPentagon.side::loe).ifPresent(builder::and);
        Optional.of(parameters.getParameter().getUserEmail())
                .map(qPentagon.createdBy.email::eq).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedFrom())
                .map(qPentagon.createdDate::after).ifPresent(builder::and);
        Optional.ofNullable(parameters.getParameter().getCreatedTo())
                .map(qPentagon.createdDate::before).ifPresent(builder::and);
        return builder;
    }

}
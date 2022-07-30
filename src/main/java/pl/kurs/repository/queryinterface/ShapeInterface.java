package pl.kurs.repository.queryinterface;

import com.querydsl.core.types.Predicate;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.command.SearchShapeCommand;


public interface ShapeInterface<T>{

    String getType();
    T mapCommandToEntity(CreateShapeCommand command);
    Predicate toPredicate(SearchShapeCommand parameters);

}

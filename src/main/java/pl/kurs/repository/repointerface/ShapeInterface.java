package pl.kurs.repository.repointerface;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.kurs.model.Shape;
import pl.kurs.model.command.SearchShapeCommand;


public interface ShapeInterface<T extends Shape> {
    String getType();
    Page<T> findAllShapes(Predicate predicate, Pageable pageable);
    Predicate toPredicate(SearchShapeCommand parameters);
}

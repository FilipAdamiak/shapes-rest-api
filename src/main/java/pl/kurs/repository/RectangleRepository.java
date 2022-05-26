package pl.kurs.repository;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.model.figures.Rectangle;

@Transactional
public interface RectangleRepository extends ShapeRepository<Rectangle> {

}

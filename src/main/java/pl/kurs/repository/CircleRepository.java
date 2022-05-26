package pl.kurs.repository;

import org.springframework.transaction.annotation.Transactional;
import pl.kurs.model.figures.Circle;

@Transactional
public interface CircleRepository extends ShapeRepository<Circle> {

}

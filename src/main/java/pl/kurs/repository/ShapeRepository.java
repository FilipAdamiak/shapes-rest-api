package pl.kurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import pl.kurs.model.Shape;

import java.util.List;

public interface ShapeRepository extends JpaRepository<Shape, Integer>,
        QuerydslPredicateExecutor<Shape> {

    List<Shape> findAllByCreatedBy_Id(Integer id);


}

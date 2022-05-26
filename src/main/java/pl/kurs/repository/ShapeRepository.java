package pl.kurs.repository;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.kurs.model.Shape;

import java.util.List;
import java.util.Optional;

@Service
public interface ShapeRepository<T extends Shape> extends JpaRepository<T, Integer>,
        QuerydslPredicateExecutor<T> {

    Page<T> findAll(Predicate predicate, Pageable pageable);

    List<T> findAllByCreatedBy_Id(Integer id);

    Optional<T> findById(Integer integer);

}

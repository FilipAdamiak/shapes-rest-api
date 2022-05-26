package pl.kurs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.kurs.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select d from User d left join fetch d.shapes where d.id = ?1")
    Optional<User> findById(Integer integer);

    @Override
    @Query("select d from User d left join fetch d.shapes")
    List<User> findAll();

    boolean existsByEmail(String s);
}

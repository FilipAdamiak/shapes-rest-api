package pl.kurs.service;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.error.EntityNotFoundException;
import pl.kurs.model.Shape;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.model.command.UpdateShapeCommand;
import pl.kurs.model.factory.TypesFactory;
import pl.kurs.repository.ShapeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShapeService {

    private final ShapeRepository shapeRepository;
    private final TypesFactory typesFactory;

    @Transactional
    public Shape addShape(CreateShapeCommand command) {
        return shapeRepository.saveAndFlush(typesFactory.findStrategy(command.getType()).mapCommandToEntity(command));
    }

    @Transactional
    public Page<Shape> findAll(Pageable pageable, SearchShapeCommand search) {
        Predicate predicate = typesFactory.findStrategy(search.getType()).toPredicate(search);
        return shapeRepository.findAll(predicate, pageable);
    }

    @Transactional
    public List<Shape> findByUser(Integer id) {
        return shapeRepository.findAllByCreatedBy_Id(id);
    }

    @Transactional
    public Shape findByShapeId(Integer id) {
        return shapeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Shape", String.valueOf(id)));
    }

    @Transactional
    public Shape editShape(Shape toEdit, UpdateShapeCommand command) {
        toEdit.setCreatedBy(command.getUser());
        toEdit.setCreatedDate(command.getDate());
        toEdit.setParameters(command.getParameter());
        toEdit.setVersion(command.getVersion());
        return shapeRepository.saveAndFlush(toEdit);
    }

}

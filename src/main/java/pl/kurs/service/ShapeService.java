package pl.kurs.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.error.EntityNotFoundException;
import pl.kurs.model.Shape;
import pl.kurs.model.command.CreateShapeCommand;
import pl.kurs.model.command.UpdateShapeCommand;
import pl.kurs.model.constants.AllEntities;
import pl.kurs.model.factory.RepoFactory;
import pl.kurs.model.command.SearchShapeCommand;
import pl.kurs.repository.repointerface.ShapeInterface;
import pl.kurs.repository.ShapeRepository;

import java.lang.reflect.Type;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShapeService<T extends Shape> {

    private final ModelMapper modelMapper;
    private final ShapeRepository<T> shapeRepository;
    private final RepoFactory<T> repoFactory;

    @Transactional
    public T addShape(CreateShapeCommand command) {
        T toSave = modelMapper.map(command, (Type) AllEntities.MAP.get(command.getType()));
        return shapeRepository.saveAndFlush(toSave);
    }

    @Transactional
    public Page<T> findAll(Pageable pageable, SearchShapeCommand search) {
        ShapeInterface<T> repo = repoFactory.findStrategy(search.getType());
        return repo.findAllShapes(repo.toPredicate(search), pageable);
    }

    @Transactional
    public List<T> findByUser(Integer id) {
        return shapeRepository.findAllByCreatedBy_Id(id);
    }

    @Transactional
    public T findByShapeId(Integer id) {
        return shapeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Shape", String.valueOf(id)));
    }

    @Transactional
    public T editShape(T toEdit, UpdateShapeCommand command) {
        toEdit.setCreatedBy(command.getUser());
        toEdit.setCreatedDate(command.getDate());
        toEdit.setParameters(command.getParameter());
        toEdit.setVersion(command.getVersion());
        return shapeRepository.saveAndFlush(toEdit);
    }

}
